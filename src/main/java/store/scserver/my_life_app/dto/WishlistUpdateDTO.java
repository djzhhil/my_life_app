package store.scserver.my_life_app.dto;

import lombok.Data;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * 更新心愿请求
 */
@Data
public class WishlistUpdateDTO {

    private String title;

    private String description;

    @DecimalMin(value = "0.01", message = "目标金额必须大于0")
    private BigDecimal targetAmount;

    private String icon;

    private String color;

    private Integer priority;

    private String targetDate;
}
