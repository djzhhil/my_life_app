package store.scserver.my_life_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.scserver.my_life_app.entity.Task;
import store.scserver.my_life_app.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/list")
    public List<Task> list() {
        return taskService.listTask(1L);
    }

    @PostMapping("/create")
    public void create(@RequestBody Task task) {
        task.setUserId(1L);
        taskService.createTask(task);
    }

    @PostMapping("/complete/{id}")
    public void complete(@PathVariable Long id) {
        taskService.completeTask(id);
    }
}
