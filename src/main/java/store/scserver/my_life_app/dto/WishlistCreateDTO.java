package store.scserver.my_life_app.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * 创建心愿请求
 */
@Data
public class WishlistCreateDTO {

    @NotBlank(message = "心愿标题不能为空")
    private String title;

    private String description;

    @NotNull(message = "目标金额不能为空")
    @DecimalMin(value = "0.01", message = "目标金额必须大于0")
    private BigDecimal targetAmount;

    private String icon;

    private String color;

    @Min(value = 1, message = "优先级最小值为1")
    @Max(value = 3, message = "优先级最大值为3")
    private Integer priority = 2;

    private String targetDate;
}
