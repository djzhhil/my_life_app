package store.scserver.my_life_app.service;

import store.scserver.my_life_app.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> listTask(Long userId);

    void createTask(Task task);

    void completeTask(Long taskId);
}
