package store.scserver.my_life_app.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.scserver.my_life_app.entity.Task;
import store.scserver.my_life_app.mapper.TaskMapper;
import store.scserver.my_life_app.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<Task> listTask(Long userId) {
        return taskMapper.selectByUserId(userId);
    }

    @Override
    public void createTask(Task task) {
        taskMapper.insert(task);
    }

    @Override
    public void completeTask(Long taskId) {
        //TODO: 完成任务
        Task task = taskMapper.selectById(taskId);
        task.setStatus("done");
        taskMapper.update(task);
    }
}
