package store.scserver.my_life_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 创建心愿请求
 */
@Data
@Schema(description = "创建心愿请求")
public class WishlistCreateDTO {

    @NotBlank(message = "心愿标题不能为空")
    @Schema(description = "心愿标题", example = "Switch 游戏机", maxLength = 100)
    private String title;

    @Schema(description = "心愿描述", example = "任天堂 Switch OLED版", maxLength = 500)
    private String description;

    @NotNull(message = "目标金额不能为空")
    @DecimalMin(value = "0.01", message = "目标金额必须大于0")
    @Schema(description = "目标金额", example = "1999.00")
    private BigDecimal targetAmount;

    @Schema(description = "图标名称", example = "game")
    private String icon;

    @Schema(description = "颜色十六进制", example = "#ff6b6b")
    private String color;

    @Min(value = 1, message = "优先级最小值为1")
    @Max(value = 3, message = "优先级最大值为3")
    @Schema(description = "优先级（1:高, 2:中, 3:低）", example = "1", allowableValues = {"1", "2", "3"})
    private Integer priority = 2;

    @Schema(description = "目标日期", example = "2026-12-31")
    private LocalDate targetDate;
}
