package store.scserver.my_life_app.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.scserver.my_life_app.entity.User;
import store.scserver.my_life_app.mapper.UserMapper;
import store.scserver.my_life_app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void addExpAndCoin(Long userId, int exp, int coin) {
        User user = userMapper.selectById(userId);
        user.setExp(user.getExp() + exp);
        user.setCoin(user.getCoin() + coin);
        userMapper.update(user);
    }
}
