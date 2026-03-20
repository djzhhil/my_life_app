package store.scserver.my_life_app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.scserver.my_life_app.common.Result;
import store.scserver.my_life_app.dto.TaskCreateDTO;
import store.scserver.my_life_app.entity.Task;
import store.scserver.my_life_app.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * 获取任务列表（暂时保留硬编码 userId=1，后续优化）
     */
    @GetMapping("/list")
    public List<Task> list() {
        return taskService.listTask(1L);
    }

    /**
     * 创建任务
     */
    @PostMapping("/create")
    public Result<Task> create(@Valid @RequestBody TaskCreateDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setExpReward(dto.getExpReward());
        task.setCoinReward(dto.getCoinReward());
        task.setUserId(1L); // 暂时保留硬编码，后续优化
        task.setStatus("pending");
        taskService.createTask(task);
        
        Result<Task> result = new Result<>();
        result.setCode(200);
        result.setMessage("创建成功");
        result.setData(task);
        return result;
    }

    /**
     * 完成任务
     */
    @PostMapping("/complete/{id}")
    public Result<Void> complete(@PathVariable Long id) {
        taskService.completeTask(id);
        return Result.success("操作成功");
    }
}