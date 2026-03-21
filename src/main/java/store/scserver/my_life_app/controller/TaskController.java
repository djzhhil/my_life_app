package store.scserver.my_life_app.controller;

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
    @GetMapping("/list")
    public List<Task> list(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return taskService.listTask(userId);
    }

    /**
     * 按分类查询任务
     */
    @GetMapping("/list/category/{category}")
    public List<Task> listByCategory(@PathVariable String category, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return taskService.listByCategory(userId, category);
    }

    /**
     * 按优先级查询任务
     */
    @GetMapping("/list/priority/{priority}")
    public List<Task> listByPriority(@PathVariable Integer priority, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return taskService.listByPriority(userId, priority);
    }

    /**
     * 创建任务
     */
    @PostMapping("/create")
    public Result<Task> create(@Valid @RequestBody TaskCreateDTO dto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setExpReward(dto.getExpReward());
        task.setCoinReward(dto.getCoinReward());
        task.setCategory(dto.getCategory() != null ? dto.getCategory() : "general");
        task.setPriority(dto.getPriority() != null ? dto.getPriority() : 1);
        task.setDueDate(dto.getDueDate());
        task.setUserId(userId);
        task.setStatus("pending");

        taskService.createTask(task);

        return Result.success("创建成功", task);
    }

    /**
     * 更新任务
     */
    @PutMapping("/update/{id}")
    public Result<Task> update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO dto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Task task = taskService.updateTask(id, userId, dto);
        return Result.success("更新成功", task);
    }

    /**
     * 删除任务
     */
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        taskService.deleteTask(id, userId);
        return Result.success("删除成功");
    }

    /**
     * 完成任务
     */
    @PostMapping("/complete/{id}")
    public Result<Void> complete(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        taskService.completeTask(id, userId);
        return Result.success("操作成功");
    }
}
