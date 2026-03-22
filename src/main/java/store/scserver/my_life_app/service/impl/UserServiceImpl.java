package store.scserver.my_life_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.scserver.my_life_app.dto.UserLoginDTO;
import store.scserver.my_life_app.dto.UserRegisterDTO;
import store.scserver.my_life_app.entity.User;
import store.scserver.my_life_app.exception.BusinessException;
import store.scserver.my_life_app.mapper.UserMapper;
import store.scserver.my_life_app.service.UserService;
import store.scserver.my_life_app.util.JwtUtils;
import store.scserver.my_life_app.vo.TokenVO;
import store.scserver.my_life_app.vo.UserVO;
import cn.hutool.core.bean.BeanUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public User getUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void addExpAndCoin(Long userId, int exp, int coin) {
        User user = userMapper.selectById(userId);
        user.setExp(user.getExp() + exp);
        user.setCoin(user.getCoin().add(BigDecimal.valueOf(coin)));
        userMapper.update(user);
    }

    @Override
    public void addCoin(Long userId, BigDecimal coin) {
        User user = userMapper.selectById(userId);
        user.setCoin(user.getCoin().add(coin));
        userMapper.update(user);
    }

    @Override
    public UserVO register(UserRegisterDTO dto) {
        // 1. 检查用户名是否已存在
        User existUser = userMapper.selectByUsername(dto.getUsername());
        if (existUser != null) {
            throw new BusinessException(400, "用户名已存在");
        }

        // 2. 创建用户对象
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(hashPassword(dto.getPassword()));
        user.setLevel(1);
        user.setExp(0);
        user.setCoin(BigDecimal.ZERO);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // 3. 保存用户
        userMapper.insert(user);

        // 4. 返回用户信息（不含密码）
        return BeanUtil.copyProperties(user, UserVO.class);
    }

    @Override
    public TokenVO login(UserLoginDTO dto) {
        // 1. 根据用户名查询用户
        User user = userMapper.selectByUsername(dto.getUsername());
        if (user == null) {
            throw new BusinessException(400, "用户名或密码错误");
        }

        // 2. 验证密码
        if (!checkPassword(dto.getPassword(), user.getPassword())) {
            throw new BusinessException(400, "用户名或密码错误");
        }

        // 3. 生成 Token
        String token = generateToken(user);

        // 4. 返回令牌
        return new TokenVO(token);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public Integer calculateLevel(Integer exp) {
        return exp / 100 + 1;
    }

    private String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    private boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateToken(User user) {
        return jwtUtils.generateToken(user.getId(), user.getUsername());
    }
}