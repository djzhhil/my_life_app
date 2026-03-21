package store.scserver.my_life_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

/**
 * 更新任务请求 DTO
 */
@Data
@Schema(description = "更新任务请求")
public class TaskUpdateDTO {

    /**
     * 任务标题（可选）
     * 长度：1-100 字符
     */
    @Size(max = 100, message = "任务标题不能超过 100 个字符")
    @Schema(description = "任务标题", example = "完成 LeetCode", maxLength = 100)
    private String title;

    /**
     * 任务描述（可选）
     * 长度：0-500 字符
     */
    @Size(max = 500, message = "任务描述不能超过 500 个字符")
    @Schema(description = "任务描述", example = "每天刷 2 道算法题", maxLength = 500)
    private String description;

    /**
     * 任务分类（可选）
     * 可选值：0-通用, 1-学习, 2-工作, 3-运动, 4-生活, 5-创意
     */
    @Schema(description = "任务分类", example = "1",
            allowableValues = {"0", "1", "2", "3", "4", "5"})
    private Integer category;

    /**
     * 优先级（可选）
     * 可选值：1-高、2-中、3-低
     */
    @Min(value = 1, message = "优先级必须在 1-3 之间")
    @Max(value = 3, message = "优先级必须在 1-3 之间")
    @Schema(description = "优先级（1:高, 2:中, 3:低）", example = "3", minimum = "1", maximum = "3")
    private Integer priority;

    /**
     * 截止日期（可选）
     */
    @Schema(description = "截止日期", example = "2026-12-31")
    private LocalDate dueDate;
}
