package store.scserver.my_life_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

/**
 * 创建任务请求 DTO
 */
@Data
@Schema(description = "创建任务请求")
public class TaskCreateDTO {

    /**
     * 任务标题
     * 长度：1-100 字符
     */
    @NotBlank(message = "任务标题不能为空")
    @Size(max = 100, message = "任务标题不能超过 100 个字符")
    @Schema(description = "任务标题", example = "完成 LeetCode", maxLength = 100)
    private String title;

    /**
     * 任务描述
     * 长度：0-500 字符（可选）
     */
    @Size(max = 500, message = "任务描述不能超过 500 个字符")
    @Schema(description = "任务描述", example = "每天刷 2 道算法题", maxLength = 500)
    private String description;

    /**
     * 经验奖励
     * 范围：1-10000
     */
    @NotNull(message = "经验奖励不能为空")
    @Min(value = 1, message = "经验奖励不能小于 1")
    @Max(value = 10000, message = "经验奖励不能超过 10000")
    @Schema(description = "经验奖励", example = "20", minimum = "1", maximum = "10000")
    private Integer expReward;

    /**
     * 金币奖励
     * 范围：1-10000
     */
    @NotNull(message = "金币奖励不能为空")
    @Min(value = 1, message = "金币奖励不能小于 1")
    @Max(value = 10000, message = "金币奖励不能超过 10000")
    @Schema(description = "金币奖励", example = "10", minimum = "1", maximum = "10000")
    private Integer coinReward;

    /**
     * 任务分类
     * 可选值：0-通用, 1-学习, 2-工作, 3-运动, 4-生活, 5-创意
     * 默认值：0
     */
    @Schema(description = "任务分类", example = "1",
            allowableValues = {"0", "1", "2", "3", "4", "5"})
    private Integer category;

    /**
     * 优先级
     * 可选值：1-高、2-中、3-低
     * 默认值：2
     */
    @Schema(description = "优先级（1:高, 2:中, 3:低）", example = "3", minimum = "1", maximum = "3")
    private Integer priority;

    /**
     * 截止日期
     * 可选
     */
    @Schema(description = "截止日期", example = "2026-12-31")
    private LocalDate dueDate;
}
