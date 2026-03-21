package store.scserver.my_life_app.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 心愿响应
 */
@Data
public class WishlistVO {

    private Long id;

    private Long userId;

    private String title;

    private String description;

    private BigDecimal targetAmount;

    private BigDecimal currentAmount;

    private Integer progress; // 进度百分比

    private String icon;

    private String color;

    private Integer priority;

    private String priorityName;

    private Integer status;

    private String statusName;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate targetDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime completedAt;
}