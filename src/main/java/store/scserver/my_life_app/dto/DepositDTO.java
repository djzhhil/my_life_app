package store.scserver.my_life_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import java.math.BigDecimal;

/**
 * 存入金币请求
 */
@Data
@Schema(description = "存入金币请求")
public class DepositDTO {

    @NotNull(message = "心愿ID不能为空")
    @Schema(description = "心愿ID", example = "1")
    private Long wishlistId;

    @NotNull(message = "存入金额不能为空")
    @DecimalMin(value = "0.01", message = "存入金额必须大于0")
    @Schema(description = "存入金额", example = "100.00")
    private BigDecimal amount;

    @Schema(description = "备注", example = "本周存钱", maxLength = 200)
    private String note;
}
