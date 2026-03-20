package store.scserver.my_life_app.service.impl;

import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.scserver.my_life_app.entity.Task;
import store.scserver.my_life_app.entity.User;
import store.scserver.my_life_app.exception.BusinessException;
import store.scserver.my_life_app.mapper.TaskMapper;
import store.scserver.my_life_app.mapper.UserMapper;
import store.scserver.my_life_app.service.TaskService;
import store.scserver.my_life_app.service.UserService;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Task> listTask(Long userId) {
        return taskMapper.selectByUserId(userId);
    }

    @Override
    public void createTask(Task task) {
        task.setStatus("pending");
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        taskMapper.insert(task);
    }

    @Transactional
    @Override
    public void completeTask(Long taskId) {
        log.info("开始完成任务，taskId: {}", taskId);

        // 1. 查询任务
        Task task = taskMapper.selectById(taskId);
        if (task == null) {
            throw new BusinessException(404, "任务不存在");
        }

        // 2. 检查任务状态
        if ("done".equals(task.getStatus())) {
            throw new BusinessException(400, "任务已完成");
        }

        // 3. 更新任务状态和完成时间
        task.setStatus("done");
        task.setCompletedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        taskMapper.update(task);
        log.info("任务状态已更新为已完成，taskId: {}", taskId);

        // 4. 发放奖励（经验值和金币）
        userService.addExpAndCoin(task.getUserId(), task.getExpReward(), task.getCoinReward());
        log.info("奖励已发放，userId: {}, exp: {}, coin: {}", task.getUserId(), task.getExpReward(), task.getCoinReward());

        // 5. 更新用户等级
        User user = userService.getUser(task.getUserId());
        Integer newLevel = userService.calculateLevel(user.getExp());
        if (!newLevel.equals(user.getLevel())) {
            user.setLevel(newLevel);
            userMapper.update(user);
            log.info("用户升级，userId: {}, 旧等级: {}, 新等级: {}", task.getUserId(), user.getLevel(), newLevel);
        }

        log.info("任务完成处理完成，taskId: {}", taskId);
    }
}