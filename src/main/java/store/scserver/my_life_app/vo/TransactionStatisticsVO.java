package store.scserver.my_life_app.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class TransactionStatisticsVO {
    private BigDecimal totalIncome;      // 总收入
    private BigDecimal totalExpense;     // 总支出
    private BigDecimal balance;          // 余额（收入-支出）
    private Integer transactionCount;    // 交易笔数
    private List<CategoryStatisticVO> categoryStatistics;  // 分类统计
    private List<DailyStatisticVO> dailyStatistics;        // 每日统计

    @Data
    public static class CategoryStatisticVO {
        private Long categoryId;
        private String categoryName;
        private String categoryIcon;
        private String categoryColor;
        private BigDecimal amount;
        private Integer count;
        private String type;  // income/expense
    }

    @Data
    public static class DailyStatisticVO {
        private String date;
        private BigDecimal income;
        private BigDecimal expense;
        private BigDecimal balance;
    }
}
