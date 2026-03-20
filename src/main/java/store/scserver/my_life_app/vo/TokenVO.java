package store.scserver.my_life_app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录令牌响应 VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenVO {

    /**
     * JWT Token
     * 用于后续请求的身份认证
     */
    private String token;
}
