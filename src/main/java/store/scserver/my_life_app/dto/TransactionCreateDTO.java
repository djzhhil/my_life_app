package store.scserver.my_life_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Schema(description = "创建交易记录请求")
public class TransactionCreateDTO {
    @NotNull(message = "类型不能为空")
    @Min(value = 1, message = "类型必须是 1（收入）或 2（支出）")
    @Max(value = 2, message = "类型必须是 1（收入）或 2（支出）")
    @Schema(description = "类型（1:收入, 2:支出）", example = "2", allowableValues = {"1", "2"})
    private Integer type;

    @NotNull(message = "金额不能为空")
    @DecimalMin(value = "0.01", message = "金额必须大于 0")
    @DecimalMax(value = "999999.99", message = "金额不能超过 999999.99")
    @Schema(description = "金额", example = "12.50")
    private BigDecimal amount;

    @NotNull(message = "分类ID不能为空")
    @Schema(description = "分类ID", example = "1")
    private Long categoryId;

    @Size(max = 500, message = "备注不能超过 500 个字符")
    @Schema(description = "备注", example = "午餐")
    private String description;

    @NotBlank(message = "账户不能为空")
    @Schema(description = "账户", example = "支付宝")
    private String account;

    @NotNull(message = "日期不能为空")
    @PastOrPresent(message = "日期不能是未来时间")
    @Schema(description = "交易日期", example = "2026-03-21")
    private LocalDate date;
}
