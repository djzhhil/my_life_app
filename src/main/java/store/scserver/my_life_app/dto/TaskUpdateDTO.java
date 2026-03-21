package store.scserver.my_life_app.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

/**
 * 更新任务请求 DTO
 */
@Data
public class TaskUpdateDTO {

    /**
     * 任务标题（可选）
     * 长度：1-100 字符
     */
    @Size(max = 100, message = "任务标题不能超过 100 个字符")
    private String title;

    /**
     * 任务描述（可选）
     * 长度：0-500 字符
     */
    @Size(max = 500, message = "任务描述不能超过 500 个字符")
    private String description;

    /**
     * 任务分类（可选）
     * 可选值：general/study/work/life/health
     */
    private String category;

    /**
     * 优先级（可选）
     * 可选值：1-低、2-中、3-高
     */
    @Min(value = 1, message = "优先级必须在 1-3 之间")
    @Max(value = 3, message = "优先级必须在 1-3 之间")
    private Integer priority;

    /**
     * 截止日期（可选）
     */
    private LocalDate dueDate;
}
