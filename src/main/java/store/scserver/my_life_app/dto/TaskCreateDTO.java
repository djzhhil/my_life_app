package store.scserver.my_life_app.dto;

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
public class TaskCreateDTO {

    /**
     * 任务标题
     * 长度：1-100 字符
     */
    @NotBlank(message = "任务标题不能为空")
    @Size(max = 100, message = "任务标题不能超过 100 个字符")
    private String title;

    /**
     * 任务描述
     * 长度：0-500 字符（可选）
     */
    @Size(max = 500, message = "任务描述不能超过 500 个字符")
    private String description;

    /**
     * 经验奖励
     * 范围：1-10000
     */
    @NotNull(message = "经验奖励不能为空")
    @Min(value = 1, message = "经验奖励不能小于 1")
    @Max(value = 10000, message = "经验奖励不能超过 10000")
    private Integer expReward;

    /**
     * 金币奖励
     * 范围：1-10000
     */
    @NotNull(message = "金币奖励不能为空")
    @Min(value = 1, message = "金币奖励不能小于 1")
    @Max(value = 10000, message = "金币奖励不能超过 10000")
    private Integer coinReward;

    /**
     * 任务分类
     * 可选值：general/study/work/life/health
     * 默认值：general
     */
    private String category;

    /**
     * 优先级
     * 可选值：1-低、2-中、3-高
     * 默认值：1
     */
    @Min(value = 1, message = "优先级必须在 1-3 之间")
    @Max(value = 3, message = "优先级必须在 1-3 之间")
    private Integer priority;

    /**
     * 截止日期
     * 可选
     */
    private LocalDate dueDate;
}
