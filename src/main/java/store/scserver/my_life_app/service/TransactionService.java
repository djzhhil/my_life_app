package store.scserver.my_life_app.service;

import store.scserver.my_life_app.dto.TransactionCreateDTO;
import store.scserver.my_life_app.vo.TransactionVO;
import store.scserver.my_life_app.vo.TransactionStatisticsVO;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    /**
     * 获取交易列表
     */
    List<TransactionVO> listTransactions(Long userId, LocalDate startDate, LocalDate endDate);

    /**
     * 添加交易记录
     */
    TransactionVO addTransaction(Long userId, TransactionCreateDTO dto);

    /**
     * 更新交易记录
     */
    TransactionVO updateTransaction(Long id, Long userId, TransactionCreateDTO dto);

    /**
     * 删除交易记录
     */
    void deleteTransaction(Long id, Long userId);

    /**
     * 获取统计分析数据
     */
    TransactionStatisticsVO getStatistics(Long userId, LocalDate startDate, LocalDate endDate);
}