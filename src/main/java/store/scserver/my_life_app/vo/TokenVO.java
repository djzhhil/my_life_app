package store.scserver.my_life_app.vo;

import lombok.Data;

@Data
public class TokenVO {
    private String token;

    public TokenVO() {}

    public TokenVO(String token) {
        this.token = token;
    }
}