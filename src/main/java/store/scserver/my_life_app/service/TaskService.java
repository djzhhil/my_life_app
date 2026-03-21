package store.scserver.my_life_app.service;

import store.scserver.my_life_app.dto.TaskUpdateDTO;
import store.scserver.my_life_app.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> listTask(Long userId);

    List<Task> listByCategory(Long userId, String category);

    List<Task> listByPriority(Long userId, Integer priority);

    void createTask(Task task);

    Task updateTask(Long taskId, Long userId, TaskUpdateDTO dto);

    void deleteTask(Long taskId, Long userId);

    void completeTask(Long taskId, Long userId);
}