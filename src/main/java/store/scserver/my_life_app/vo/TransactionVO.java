package store.scserver.my_life_app.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TransactionVO {
    private Long id;
    private String type;
    private BigDecimal amount;
    private Long categoryId;
    private String categoryName;
    private String categoryIcon;
    private String categoryColor;
    private String description;
    private String account;
    private LocalDate date;
    private LocalDateTime createdAt;
}
