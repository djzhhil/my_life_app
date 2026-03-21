package store.scserver.my_life_app.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Transaction {
    private Long id;
    private Long userId;
    private String type;  // income/expense
    private BigDecimal amount;
    private Long categoryId;
    private String categoryName;
    private String description;
    private String account;
    private LocalDate date;
    private LocalDateTime createdAt;
}
