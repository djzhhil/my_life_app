package store.scserver.my_life_app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.scserver.my_life_app.dto.TaskUpdateDTO;
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
    public List<Task> listByCategory(Long userId, String category) {
        return taskMapper.selectByUserIdAndCategory(userId, category);
    }

    @Override
    public List<Task> listByPriority(Long userId, Integer priority) {
        return taskMapper.selectByUserIdAndPriority(userId, priority);
    }

    @Override
    public void createTask(Task task) {
        if (task.getStatus() == null || task.getStatus().isEmpty()) {
            task.setStatus("pending");
        }
        if (task.getCategory() == null) {
            task.setCategory(0);  // 0: 通用
        }
        if (task.getPriority() == null) {
            task.setPriority(2);  // 2: 中（原设计是1，但数据库注释是3-低、2-中、1-高）
        }
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        taskMapper.insert(task);
    }

    @Override
    public Task updateTask(Long taskId, Long userId, TaskUpdateDTO dto) {
        // 验证任务是否属于当前用户
        Task task = taskMapper.selectById(taskId);
        if (task == null) {
            throw new BusinessException(404, "任务不存在");
        }
        if (!task.getUserId().equals(userId)) {
            throw new BusinessException(403, "无权修改此任务");
        }
        // 更新字段
        if (dto.getTitle() != null) {
            task.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            task.setDescription(dto.getDescription());
        }
        if (dto.getCategory() != null) {
            task.setCategory(dto.getCategory());
        }
        if (dto.getDueDate() != null) {
            task.setDueDate(dto.getDueDate());
        }
        if (dto.getPriority() != null) {
            task.setPriority(dto.getPriority());
        }
        task.setUpdatedAt(LocalDateTime.now());
        taskMapper.update(task);
        return task;
    }

    @Override
    public void deleteTask(Long taskId, Long userId) {
        // 验证任务是否属于当前用户
        Task task = taskMapper.selectById(taskId);
        if (task == null) {
            throw new BusinessException(404, "任务不存在");
        }
        if (!task.getUserId().equals(userId)) {
            throw new BusinessException(403, "无权删除此任务");
        }
        taskMapper.deleteById(taskId);
    }

    @Transactional
    @Override
    public void completeTask(Long taskId, Long userId) {
        log.info("开始完成任务，taskId: {}, userId: {}", taskId, userId);
        // 1. 查询任务
        Task task = taskMapper.selectById(taskId);
        if (task == null) {
            throw new BusinessException(404, "任务不存在");
        }
        // 2. 验证任务是否属于当前用户
        if (!task.getUserId().equals(userId)) {
            throw new BusinessException(403, "无权完成此任务");
        }
        // 3. 检查任务状态
        if ("done".equals(task.getStatus())) {
            throw new BusinessException(400, "任务已完成");
        }
        // 4. 更新任务状态和完成时间
        task.setStatus("done");
        task.setCompletedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        taskMapper.update(task);
        log.info("任务状态已更新为已完成，taskId: {}", taskId);
        // 5. 发放奖励（经验值和金币）
        userService.addExpAndCoin(task.getUserId(), task.getExpReward(), task.getCoinReward());
        log.info("奖励已发放，userId: {}, exp: {}, coin: {}", task.getUserId(), task.getExpReward(), task.getCoinReward());
        // 6. 更新用户等级
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