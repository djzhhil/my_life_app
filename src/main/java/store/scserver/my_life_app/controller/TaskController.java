package store.scserver.my_life_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.scserver.my_life_app.common.Result;
import store.scserver.my_life_app.dto.TaskCreateDTO;
import store.scserver.my_life_app.dto.TaskUpdateDTO;
import store.scserver.my_life_app.entity.Task;
import store.scserver.my_life_app.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 获取任务列表
     */
    @Operation(summary = "获取任务列表", description = "获取当前用户的所有任务", security = @SecurityRequirement(name = "BearerAuth"))
    @GetMapping("/list")
    public List<Task> list(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return taskService.listTask(userId);
    }

    /**
     * 按分类查询任务
     */
    @Operation(summary = "按分类查询任务", description = "根据分类查询用户任务（0:通用, 1:学习, 2:工作, 3:运动, 4:生活, 5:创意）", security = @SecurityRequirement(name = "BearerAuth"))
    @GetMapping("/list/category/{category}")
    public List<Task> listByCategory(@PathVariable Integer category, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return taskService.listByCategory(userId, category);
    }

    /**
     * 按优先级查询任务
     */
    @Operation(summary = "按优先级查询任务", description = "根据优先级查询用户任务（1:高, 2:中, 3:低）", security = @SecurityRequirement(name = "BearerAuth"))
    @GetMapping("/list/priority/{priority}")
    public List<Task> listByPriority(@PathVariable Integer priority, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return taskService.listByPriority(userId, priority);
    }

    /**
     * 创建任务
     */
    @Operation(summary = "创建任务", description = "创建新任务", security = @SecurityRequirement(name = "BearerAuth"))
    @PostMapping("/create")
    public Result<Task> create(@Valid @RequestBody TaskCreateDTO dto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setExpReward(dto.getExpReward());
        task.setCoinReward(dto.getCoinReward());
        task.setCategory(dto.getCategory() != null ? dto.getCategory() : 0);  // 0: 通用
        task.setPriority(dto.getPriority() != null ? dto.getPriority() : 2);  // 2: 中
        task.setDueDate(dto.getDueDate());
        task.setUserId(userId);
        task.setStatus(0);  // 0: pending (待完成)

        taskService.createTask(task);

        return Result.success("创建成功", task);
    }

    /**
     * 更新任务
     */
    @Operation(summary = "更新任务", description = "更新任务信息", security = @SecurityRequirement(name = "BearerAuth"))
    @PutMapping("/update/{id}")
    public Result<Task> update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO dto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Task task = taskService.updateTask(id, userId, dto);
        return Result.success("更新成功", task);
    }

    /**
     * 删除任务
     */
    @Operation(summary = "删除任务", description = "删除指定任务", security = @SecurityRequirement(name = "BearerAuth"))
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        taskService.deleteTask(id, userId);
        return Result.success("删除成功");
    }

    /**
     * 完成任务
     */
    @Operation(summary = "完成任务", description = "完成任务并发放奖励", security = @SecurityRequirement(name = "BearerAuth"))
    @PostMapping("/complete/{id}")
    public Result<Void> complete(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        taskService.completeTask(id, userId);
        return Result.success("操作成功");
    }
}
