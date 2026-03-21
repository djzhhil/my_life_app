package store.scserver.my_life_app.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Schema(description = "分类实体")
public class Category {
    @Schema(description = "分类ID")
    private Long id;
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "分类名称", example = "餐饮")
    private String name;
    
    @Schema(description = "类型（1:收入, 2:支出）", example = "2", allowableValues = {"1", "2"})
    private Integer type;
    
    @Schema(description = "图标名称", example = "food")
    private String icon;
    
    @Schema(description = "颜色", example = "#ff6b6b")
    private String color;
    
    @Schema(description = "排序", example = "1")
    private Integer sortOrder;
    
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
}
