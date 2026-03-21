package store.scserver.my_life_app.mapper;

import org.apache.ibatis.annotations.Mapper;
import store.scserver.my_life_app.entity.Task;
import java.util.List;

@Mapper
public interface TaskMapper {
    List<Task> selectByUserId(Long userId);
    List<Task> selectByUserIdAndCategory(Long userId, Integer category);
    List<Task> selectByUserIdAndPriority(Long userId, Integer priority);
    Task selectById(Long id);
    void insert(Task task);
    void update(Task task);
    void deleteById(Long id);
}