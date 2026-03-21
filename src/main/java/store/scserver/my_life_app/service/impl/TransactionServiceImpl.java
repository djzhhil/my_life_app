package store.scserver.my_life_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.scserver.my_life_app.dto.TransactionCreateDTO;
import store.scserver.my_life_app.entity.Category;
import store.scserver.my_life_app.entity.Transaction;
import store.scserver.my_life_app.exception.BusinessException;
import store.scserver.my_life_app.mapper.CategoryMapper;
import store.scserver.my_life_app.mapper.TransactionMapper;
import store.scserver.my_life_app.service.TransactionService;
import store.scserver.my_life_app.vo.TransactionStatisticsVO;
import store.scserver.my_life_app.vo.TransactionVO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<TransactionVO> listTransactions(Long userId, LocalDate startDate, LocalDate endDate) {
        List<Transaction> transactions = transactionMapper.selectByUserId(userId, startDate, endDate);
        return transactions.stream().map(this::convertToVO).toList();
    }

    @Override
    public TransactionVO addTransaction(Long userId, TransactionCreateDTO dto) {
        // 验证分类是否存在
        Category category = categoryMapper.selectById(dto.getCategoryId());
        if (category == null) {
            throw new BusinessException(404, "分类不存在");
        }

        // 验证类型匹配
        if (!category.getType().equals(dto.getType())) {
            throw new BusinessException(400, "交易类型与分类类型不匹配");
        }

        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setType(dto.getType());
        transaction.setAmount(dto.getAmount());
        transaction.setCategoryId(dto.getCategoryId());
        transaction.setCategoryName(category.getName());
        transaction.setDescription(dto.getDescription());
        transaction.setAccount(dto.getAccount());
        transaction.setDate(dto.getDate());

        transactionMapper.insert(transaction);

        return convertToVO(transaction);
    }

    @Override
    public TransactionVO updateTransaction(Long id, Long userId, TransactionCreateDTO dto) {
        // 验证交易记录存在且属于当前用户
        Transaction existing = transactionMapper.selectById(id);
        if (existing == null) {
            throw new BusinessException(404, "交易记录不存在");
        }
        if (!existing.getUserId().equals(userId)) {
            throw new BusinessException(403, "无权修改此交易记录");
        }

        // 验证分类是否存在
        Category category = categoryMapper.selectById(dto.getCategoryId());
        if (category == null) {
            throw new BusinessException(404, "分类不存在");
        }

        // 验证类型匹配
        if (!category.getType().equals(dto.getType())) {
            throw new BusinessException(400, "交易类型与分类类型不匹配");
        }

        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setUserId(userId);
        transaction.setType(dto.getType());
        transaction.setAmount(dto.getAmount());
        transaction.setCategoryId(dto.getCategoryId());
        transaction.setCategoryName(category.getName());
        transaction.setDescription(dto.getDescription());
        transaction.setAccount(dto.getAccount());
        transaction.setDate(dto.getDate());

        transactionMapper.update(transaction);

        return convertToVO(transaction);
    }

    @Override
    public void deleteTransaction(Long id, Long userId) {
        Transaction existing = transactionMapper.selectById(id);
        if (existing == null) {
            throw new BusinessException(404, "交易记录不存在");
        }
        if (!existing.getUserId().equals(userId)) {
            throw new BusinessException(403, "无权删除此交易记录");
        }
        transactionMapper.deleteById(id);
    }

    @Override
    public TransactionStatisticsVO getStatistics(Long userId, LocalDate startDate, LocalDate endDate) {
        TransactionStatisticsVO vo = new TransactionStatisticsVO();

        // 计算总收入和总支出
        BigDecimal totalIncome = transactionMapper.sumIncome(userId, startDate, endDate);
        BigDecimal totalExpense = transactionMapper.sumExpense(userId, startDate, endDate);

        vo.setTotalIncome(totalIncome != null ? totalIncome : BigDecimal.ZERO);
        vo.setTotalExpense(totalExpense != null ? totalExpense : BigDecimal.ZERO);
        vo.setBalance(vo.getTotalIncome().subtract(vo.getTotalExpense()));

        // 交易笔数
        Integer count = transactionMapper.countByUserId(userId, startDate, endDate);
        vo.setTransactionCount(count != null ? count : 0);

        // 分类统计
        List<TransactionStatisticsVO.CategoryStatisticVO> categoryStats =
                transactionMapper.statisticsByCategory(userId, startDate, endDate);
        vo.setCategoryStatistics(categoryStats);

        // 每日统计
        List<TransactionStatisticsVO.DailyStatisticVO> dailyStats =
                transactionMapper.statisticsByDate(userId, startDate, endDate);
        vo.setDailyStatistics(dailyStats);

        return vo;
    }

    private TransactionVO convertToVO(Transaction transaction) {
        TransactionVO vo = new TransactionVO();
        vo.setId(transaction.getId());
        vo.setType(transaction.getType());
        vo.setAmount(transaction.getAmount());
        vo.setCategoryId(transaction.getCategoryId());
        vo.setCategoryName(transaction.getCategoryName());
        vo.setDescription(transaction.getDescription());
        vo.setAccount(transaction.getAccount());
        vo.setDate(transaction.getDate());
        vo.setCreatedAt(transaction.getCreatedAt());

        // 补充分类的图标和颜色
        if (transaction.getCategoryId() != null) {
            Category category = categoryMapper.selectById(transaction.getCategoryId());
            if (category != null) {
                vo.setCategoryIcon(category.getIcon());
                vo.setCategoryColor(category.getColor());
            }
        }

        return vo;
    }
}