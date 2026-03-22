package store.scserver.my_life_app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import store.scserver.my_life_app.enums.TaskCategory;
import store.scserver.my_life_app.enums.TaskPriority;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Task {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private Integer expReward;
    private Integer coinReward;
    private Integer category;
    private Integer priority;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate dueDate;
    private Integer status;  // 0=pending, 1=done
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime completedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedAt;

    /**
     * 获取分类名称
     */
    public String getCategoryName() {
        return TaskCategory.getNameByCode(category);
    }

    /**
     * 获取优先级名称
     */
    public String getPriorityName() {
        return TaskPriority.getNameByCode(priority);
    }
}