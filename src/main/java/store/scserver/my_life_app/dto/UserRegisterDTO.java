package store.scserver.my_life_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 用户注册请求 DTO
 */
@Data
public class UserRegisterDTO {

    /**
     * 用户名
     * 长度：2-20 字符
     */
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度必须在 2-20 个字符之间")
    private String username;

    /**
     * 密码
     * 长度：6-30 字符
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 30, message = "密码长度必须在 6-30 个字符之间")
    private String password;
}
