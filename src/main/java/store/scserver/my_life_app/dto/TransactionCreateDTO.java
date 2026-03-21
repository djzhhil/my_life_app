package store.scserver.my_life_app.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionCreateDTO {
    @NotBlank(message = "类型不能为空")
    @Pattern(regexp = "income|expense", message = "类型必须是 income 或 expense")
    private String type;

    @NotNull(message = "金额不能为空")
    @DecimalMin(value = "0.01", message = "金额必须大于 0")
    @DecimalMax(value = "999999.99", message = "金额不能超过 999999.99")
    private BigDecimal amount;

    @NotNull(message = "分类ID不能为空")
    private Long categoryId;

    @Size(max = 500, message = "备注不能超过 500 个字符")
    private String description;

    @NotBlank(message = "账户不能为空")
    private String account;

    @NotNull(message = "日期不能为空")
    @PastOrPresent(message = "日期不能是未来时间")
    private LocalDate date;
}
