package store.scserver.my_life_app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WishlistDeposit {
    private Long id;
    private Long wishlistId;
    private Long userId;
    private BigDecimal amount;
    private String note;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;
}
