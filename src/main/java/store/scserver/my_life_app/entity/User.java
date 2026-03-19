package store.scserver.my_life_app.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private Integer level;
    private Integer exp;
    private Integer coin;
}
