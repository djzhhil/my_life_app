package store.scserver.my_life_app.mapper;

import org.apache.ibatis.annotations.Mapper;
import store.scserver.my_life_app.entity.User;

@Mapper
public interface UserMapper {

    User selectById(Long id);

    void update(User user);
}
