package store.scserver.my_life_app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Wishlist {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private BigDecimal targetAmount;
    private BigDecimal currentAmount;
    private String icon;
    private String color;
    private Integer priority;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate targetDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime completedAt;

    /**
     * 获取进度百分比
     */
    public Integer getProgress() {
        if (targetAmount == null || targetAmount.compareTo(BigDecimal.ZERO) == 0) {
            return 0;
        }
        return currentAmount.multiply(new BigDecimal("100"))
                .divide(targetAmount, 0, BigDecimal.ROUND_DOWN)
                .intValue();
    }

    /**
     * 是否已完成
     */
    public boolean isCompleted() {
        return status != null && status == 1;
    }

    /**
     * 获取优先级名称
     */
    public String getPriorityName() {
        if (priority == null) {
            return "中";
        }
        switch (priority) {
            case 1: return "高";
            case 2: return "中";
            case 3: return "低";
            default: return "中";
        }
    }

    /**
     * 获取状态名称
     */
    public String getStatusName() {
        if (status == null) {
            return "进行中";
        }
        switch (status) {
            case 0: return "进行中";
            case 1: return "已实现";
            case 2: return "已放弃";
            default: return "进行中";
        }
    }
}
