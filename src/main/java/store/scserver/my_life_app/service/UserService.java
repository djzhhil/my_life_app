package store.scserver.my_life_app.service;

import store.scserver.my_life_app.dto.UserLoginDTO;
import store.scserver.my_life_app.dto.UserRegisterDTO;
import store.scserver.my_life_app.entity.User;
import store.scserver.my_life_app.vo.TokenVO;
import store.scserver.my_life_app.vo.UserVO;
import java.math.BigDecimal;

public interface UserService {
    User getUser(Long id);
    void addExpAndCoin(Long userId, int exp, int coin);
    void addCoin(Long userId, BigDecimal coin);
    UserVO register(UserRegisterDTO dto);
    TokenVO login(UserLoginDTO dto);
    User getByUsername(String username);
    Integer calculateLevel(Integer exp);
}