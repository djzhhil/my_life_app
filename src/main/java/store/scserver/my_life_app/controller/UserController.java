package store.scserver.my_life_app.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.scserver.my_life_app.common.Result;
import store.scserver.my_life_app.dto.UserLoginDTO;
import store.scserver.my_life_app.dto.UserRegisterDTO;
import store.scserver.my_life_app.entity.User;
import store.scserver.my_life_app.service.UserService;
import store.scserver.my_life_app.util.UserContext;
import store.scserver.my_life_app.vo.TokenVO;
import store.scserver.my_life_app.vo.UserVO;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<UserVO> register(@Valid @RequestBody UserRegisterDTO dto) {
        UserVO userVO = userService.register(dto);
        return Result.success("注册成功", userVO);
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<TokenVO> login(@Valid @RequestBody UserLoginDTO dto) {
        TokenVO tokenVO = userService.login(dto);
        return Result.success("登录成功", tokenVO);
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/info")
    public User getUser(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId != null) {
            UserContext.setCurrentUserId(userId);
        }
        return userService.getUser(userId);
    }
}
