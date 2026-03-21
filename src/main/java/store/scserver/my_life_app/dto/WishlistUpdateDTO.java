package store.scserver.my_life_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 更新心愿请求
 */
@Data
@Schema(description = "更新心愿请求")
public class WishlistUpdateDTO {

    @Schema(description = "心愿标题", example = "Switch 游戏机", maxLength = 100)
    private String title;

    @Schema(description = "心愿描述", example = "任天堂 Switch OLED版", maxLength = 500)
    private String description;

    @DecimalMin(value = "0.01", message = "目标金额必须大于0")
    @Schema(description = "目标金额", example = "1999.00")
    private BigDecimal targetAmount;

    @Schema(description = "图标名称", example = "game")
    private String icon;

    @Schema(description = "颜色十六进制", example = "#ff6b6b")
    private String color;

    @Schema(description = "优先级（1:高, 2:中, 3:低）", example = "1", allowableValues = {"1", "2", "3"})
    private Integer priority;

    @Schema(description = "目标日期", example = "2026-12-31")
    private LocalDate targetDate;
}
