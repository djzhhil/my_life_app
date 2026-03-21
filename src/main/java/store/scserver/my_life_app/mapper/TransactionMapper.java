package store.scserver.my_life_app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import store.scserver.my_life_app.entity.Transaction;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface TransactionMapper {

    /**
     * 插入交易记录
     */
    int insert(Transaction transaction);

    /**
     * 根据ID查询交易记录
     */
    Transaction selectById(Long id);

    /**
     * 根据用户ID查询交易列表（支持日期范围）
     */
    List<Transaction> selectByUserId(@Param("userId") Long userId,
                                      @Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate);

    /**
     * 根据用户ID和类型查询交易列表
     * @param type 1:收入, 2:支出
     */
    List<Transaction> selectByUserIdAndType(@Param("userId") Long userId,
                                             @Param("type") Integer type,
                                             @Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate);

    /**
     * 根据用户ID和分类ID查询交易列表
     */
    List<Transaction> selectByUserIdAndCategoryId(@Param("userId") Long userId,
                                                   @Param("categoryId") Long categoryId,
                                                   @Param("startDate") LocalDate startDate,
                                                   @Param("endDate") LocalDate endDate);

    /**
     * 更新交易记录
     */
    int update(Transaction transaction);

    /**
     * 删除交易记录
     */
    int deleteById(Long id);

    /**
     * 统计用户总收入
     */
    java.math.BigDecimal sumIncome(@Param("userId") Long userId,
                                    @Param("startDate") LocalDate startDate,
                                    @Param("endDate") LocalDate endDate);

    /**
     * 统计用户总支出
     */
    java.math.BigDecimal sumExpense(@Param("userId") Long userId,
                                     @Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate);

    /**
     * 统计交易笔数
     */
    Integer countByUserId(@Param("userId") Long userId,
                          @Param("startDate") LocalDate startDate,
                          @Param("endDate") LocalDate endDate);

    /**
     * 按分类统计
     */
    List<store.scserver.my_life_app.vo.TransactionStatisticsVO.CategoryStatisticVO> statisticsByCategory(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    /**
     * 按日期统计
     */
    List<store.scserver.my_life_app.vo.TransactionStatisticsVO.DailyStatisticVO> statisticsByDate(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
