package store.scserver.my_life_app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Schema(description = "交易记录返回值")
public class TransactionVO {
    @Schema(description = "交易ID")
    private Long id;
    
    @Schema(description = "类型（1:收入, 2:支出）", example = "2")
    private Integer type;
    
    @Schema(description = "金额", example = "12.50")
    private BigDecimal amount;
    
    @Schema(description = "分类ID", example = "1")
    private Long categoryId;
    
    @Schema(description = "分类名称", example = "餐饮")
    private String categoryName;
    
    @Schema(description = "分类图标", example = "food")
    private String categoryIcon;
    
    @Schema(description = "分类颜色", example = "#ff6b6b")
    private String categoryColor;
    
    @Schema(description = "备注", example = "午餐")
    private String description;
    
    @Schema(description = "账户", example = "支付宝")
    private String account;
    
    @Schema(description = "交易日期", example = "2026-03-21")
    private LocalDate date;
    
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
}
