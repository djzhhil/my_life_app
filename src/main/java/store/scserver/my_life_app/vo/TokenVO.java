package store.scserver.my_life_app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "JWT Token 返回值")
public class TokenVO {
    
    @Schema(description = "JWT Token，用于后续接口认证", 
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxIiwidXNlcm5hbWUiOiJ0ZXN0dXNlciJ9.xxx")
    private String token;

    public TokenVO() {}

    public TokenVO(String token) {
        this.token = token;
    }
}