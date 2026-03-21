package store.scserver.my_life_app.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * 存入金币请求
 */
@Data
public class DepositDTO {

    @NotNull(message = "心愿ID不能为空")
    private Long wishlistId;

    @NotNull(message = "存入金额不能为空")
    @DecimalMin(value = "0.01", message = "存入金额必须大于0")
    private BigDecimal amount;

    private String note;
}
