package store.scserver.my_life_app.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Category {
    private Long id;
    private String name;
    private String type;  // income/expense
    private String icon;
    private String color;
    private Integer sortOrder;
    private LocalDateTime createdAt;
}
