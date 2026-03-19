package store.scserver.my_life_app.entity;

import lombok.Data;

@Data
public class Task {
    private Long id;
    private Long userId;
    private String title;
    private Integer expReward;
    private Integer coinReward;
    private String status;
}
