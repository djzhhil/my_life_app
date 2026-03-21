package store.scserver.my_life_app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.scserver.my_life_app.common.Result;
import store.scserver.my_life_app.dto.TransactionCreateDTO;
import store.scserver.my_life_app.service.TransactionService;
import store.scserver.my_life_app.vo.TransactionStatisticsVO;
import store.scserver.my_life_app.vo.TransactionVO;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * 获取交易列表
     */
    @GetMapping("/list")
    public List<TransactionVO> list(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            userId = 1L;  // 暂时保留硬编码，后续 Phase 1 JWT 实现后移除
        }
        return transactionService.listTransactions(userId, startDate, endDate);
    }

    /**
     * 记一笔
     */
    @PostMapping("/add")
    public Result<TransactionVO> add(@Valid @RequestBody TransactionCreateDTO dto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            userId = 1L;  // 暂时保留硬编码，后续 Phase 1 JWT 实现后移除
        }
        TransactionVO transactionVO = transactionService.addTransaction(userId, dto);
        return Result.success("记账成功", transactionVO);
    }

    /**
     * 更新交易记录
     */
    @PutMapping("/update/{id}")
    public Result<TransactionVO> update(@PathVariable Long id, @RequestBody TransactionCreateDTO dto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            userId = 1L;  // 暂时保留硬编码，后续 Phase 1 JWT 实现后移除
        }
        TransactionVO transactionVO = transactionService.updateTransaction(id, userId, dto);
        return Result.success("更新成功", transactionVO);
    }

    /**
     * 删除交易记录
     */
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            userId = 1L;  // 暂时保留硬编码，后续 Phase 1 JWT 实现后移除
        }
        transactionService.deleteTransaction(id, userId);
        return Result.success("删除成功");
    }

    /**
     * 获取统计分析数据
     */
    @GetMapping("/statistics")
    public Result<TransactionStatisticsVO> getStatistics(
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            userId = 1L;  // 暂时保留硬编码，后续 Phase 1 JWT 实现后移除
        }
        TransactionStatisticsVO statistics = transactionService.getStatistics(userId, startDate, endDate);
        return Result.success("查询成功", statistics);
    }
}