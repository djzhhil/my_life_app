package store.scserver.my_life_app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.scserver.my_life_app.dto.DepositDTO;
import store.scserver.my_life_app.dto.WishlistCreateDTO;
import store.scserver.my_life_app.dto.WishlistUpdateDTO;
import store.scserver.my_life_app.entity.Wishlist;
import store.scserver.my_life_app.entity.WishlistDeposit;
import store.scserver.my_life_app.entity.User;
import store.scserver.my_life_app.exception.BusinessException;
import store.scserver.my_life_app.mapper.UserMapper;
import store.scserver.my_life_app.mapper.WishlistMapper;
import store.scserver.my_life_app.service.WishlistService;
import store.scserver.my_life_app.service.UserService;
import store.scserver.my_life_app.vo.WishlistVO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistMapper wishlistMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public Wishlist create(Long userId, WishlistCreateDTO dto) {
        log.info("创建心愿，userId: {}, title: {}", userId, dto.getTitle());

        // 验证目标金额
        if (dto.getTargetAmount() == null || dto.getTargetAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(400, "目标金额必须大于0");
        }

        // 设置默认值
        if (dto.getPriority() == null) {
            dto.setPriority(2);
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(userId);
        wishlist.setTitle(dto.getTitle());
        wishlist.setDescription(dto.getDescription());
        wishlist.setTargetAmount(dto.getTargetAmount());
        wishlist.setCurrentAmount(BigDecimal.ZERO);
        wishlist.setIcon(dto.getIcon());
        wishlist.setColor(dto.getColor());
        wishlist.setPriority(dto.getPriority());
        wishlist.setStatus(0); // 进行中
        wishlist.setTargetDate(dto.getTargetDate());
        wishlist.setCreatedAt(LocalDateTime.now());
        wishlist.setUpdatedAt(LocalDateTime.now());

        wishlistMapper.insert(wishlist);
        log.info("心愿创建成功，id: {}", wishlist.getId());

        return wishlist;
    }

    @Override
    public List<WishlistVO> list(Long userId, Integer status) {
        log.info("查询心愿列表，userId: {}, status: {}", userId, status);

        List<Wishlist> wishlists = wishlistMapper.selectByUserId(userId, status);
        List<WishlistVO> vos = new ArrayList<>();

        for (Wishlist w : wishlists) {
            WishlistVO vo = convertToVO(w);
            vos.add(vo);
        }

        return vos;
    }

    @Override
    public WishlistVO getDetail(Long userId, Long wishlistId) {
        log.info("获取心愿详情，userId: {}, wishlistId: {}", userId, wishlistId);

        Wishlist wishlist = wishlistMapper.selectById(wishlistId);
        if (wishlist == null) {
            throw new BusinessException(404, "心愿不存在");
        }

        if (!wishlist.getUserId().equals(userId)) {
            throw new BusinessException(403, "无权查看此心愿");
        }

        return convertToVO(wishlist);
    }

    @Override
    @Transactional
    public void deposit(Long userId, DepositDTO dto) {
        log.info("存入金币，userId: {}, wishlistId: {}, amount: {}", userId, dto.getWishlistId(), dto.getAmount());

        // 验证心愿
        Wishlist wishlist = wishlistMapper.selectById(dto.getWishlistId());
        if (wishlist == null) {
            throw new BusinessException(404, "心愿不存在");
        }

        if (!wishlist.getUserId().equals(userId)) {
            throw new BusinessException(403, "无权操作此心愿");
        }

        // 检查心愿状态
        if (wishlist.getStatus() != null && wishlist.getStatus() == 1) {
            throw new BusinessException(400, "心愿已完成，无法继续存钱");
        }

        if (wishlist.getStatus() != null && wishlist.getStatus() == 2) {
            throw new BusinessException(400, "心愿已放弃，无法继续存钱");
        }

        // 验证存入金额
        if (dto.getAmount() == null || dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(400, "存入金额必须大于0");
        }

        // 获取用户金币余额
        User user = userService.getUser(userId);
        if (user.getCoin().compareTo(dto.getAmount()) < 0) {
            throw new BusinessException(400, "金币不足");
        }

        // 扣除用户金币
        userService.addCoin(userId, dto.getAmount().negate());
        log.info("用户金币已扣除，userId: {}, 扣除金额: {}", userId, dto.getAmount());

        // 创建存钱记录
        WishlistDeposit deposit = new WishlistDeposit();
        deposit.setWishlistId(dto.getWishlistId());
        deposit.setUserId(userId);
        deposit.setAmount(dto.getAmount());
        deposit.setNote(dto.getNote());
        deposit.setCreatedAt(LocalDateTime.now());
        wishlistMapper.insertDeposit(deposit);

        // 更新心愿当前金额
        BigDecimal newAmount = wishlist.getCurrentAmount().add(dto.getAmount());
        Integer newStatus = wishlist.getStatus();
        LocalDateTime completedAt = null;

        // 检查是否达成目标
        if (newAmount.compareTo(wishlist.getTargetAmount()) >= 0) {
            newStatus = 1; // 已实现
            completedAt = LocalDateTime.now();
            log.info("心愿已达成，wishlistId: {}", dto.getWishlistId());
        }

        wishlistMapper.updateAmount(dto.getWishlistId(), newAmount, newStatus, completedAt);
        log.info("存钱成功，wishlistId: {}, 新金额: {}", dto.getWishlistId(), newAmount);
    }

    @Override
    @Transactional
    public Wishlist update(Long userId, Long wishlistId, WishlistUpdateDTO dto) {
        log.info("更新心愿，userId: {}, wishlistId: {}", userId, wishlistId);

        Wishlist wishlist = wishlistMapper.selectById(wishlistId);
        if (wishlist == null) {
            throw new BusinessException(404, "心愿不存在");
        }

        if (!wishlist.getUserId().equals(userId)) {
            throw new BusinessException(403, "无权修改此心愿");
        }

        // 检查状态
        if (wishlist.getStatus() != null && wishlist.getStatus() == 1) {
            throw new BusinessException(400, "已完成的心愿无法修改");
        }

        // 更新字段
        if (dto.getTitle() != null) {
            wishlist.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null) {
            wishlist.setDescription(dto.getDescription());
        }
        if (dto.getTargetAmount() != null) {
            if (dto.getTargetAmount().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BusinessException(400, "目标金额必须大于0");
            }
            wishlist.setTargetAmount(dto.getTargetAmount());
        }
        if (dto.getIcon() != null) {
            wishlist.setIcon(dto.getIcon());
        }
        if (dto.getColor() != null) {
            wishlist.setColor(dto.getColor());
        }
        if (dto.getPriority() != null) {
            wishlist.setPriority(dto.getPriority());
        }
        if (dto.getTargetDate() != null) {
            wishlist.setTargetDate(dto.getTargetDate());
        }

        wishlist.setUpdatedAt(LocalDateTime.now());
        wishlistMapper.update(wishlist);

        log.info("心愿更新成功，id: {}", wishlistId);
        return wishlist;
    }

    @Override
    @Transactional
    public void delete(Long userId, Long wishlistId) {
        log.info("删除心愿，userId: {}, wishlistId: {}", userId, wishlistId);

        Wishlist wishlist = wishlistMapper.selectById(wishlistId);
        if (wishlist == null) {
            throw new BusinessException(404, "心愿不存在");
        }

        if (!wishlist.getUserId().equals(userId)) {
            throw new BusinessException(403, "无权删除此心愿");
        }

        wishlistMapper.deleteById(wishlistId);
        log.info("心愿删除成功，id: {}", wishlistId);
    }

    @Override
    @Transactional
    public void complete(Long userId, Long wishlistId) {
        log.info("完成心愿，userId: {}, wishlistId: {}", userId, wishlistId);

        Wishlist wishlist = wishlistMapper.selectById(wishlistId);
        if (wishlist == null) {
            throw new BusinessException(404, "心愿不存在");
        }

        if (!wishlist.getUserId().equals(userId)) {
            throw new BusinessException(403, "无权操作此心愿");
        }

        if (wishlist.getStatus() != null && wishlist.getStatus() == 1) {
            throw new BusinessException(400, "心愿已完成");
        }

        // 更新状态
        wishlist.setStatus(1);
        wishlist.setCompletedAt(LocalDateTime.now());
        wishlist.setUpdatedAt(LocalDateTime.now());
        wishlistMapper.update(wishlist);

        log.info("心愿已完成，id: {}", wishlistId);
    }

    @Override
    @Transactional
    public void abandon(Long userId, Long wishlistId) {
        log.info("放弃心愿，userId: {}, wishlistId: {}", userId, wishlistId);

        Wishlist wishlist = wishlistMapper.selectById(wishlistId);
        if (wishlist == null) {
            throw new BusinessException(404, "心愿不存在");
        }

        if (!wishlist.getUserId().equals(userId)) {
            throw new BusinessException(403, "无权操作此心愿");
        }

        if (wishlist.getStatus() != null && wishlist.getStatus() == 1) {
            throw new BusinessException(400, "已完成的心愿无法放弃");
        }

        // 将已存金额退回给用户
        if (wishlist.getCurrentAmount() != null && wishlist.getCurrentAmount().compareTo(BigDecimal.ZERO) > 0) {
            userService.addCoin(userId, wishlist.getCurrentAmount());
            log.info("退回金币，userId: {}, 退回金额: {}", userId, wishlist.getCurrentAmount());
        }

        // 更新状态
        wishlist.setStatus(2); // 已放弃
        wishlist.setUpdatedAt(LocalDateTime.now());
        wishlistMapper.update(wishlist);

        log.info("心愿已放弃，id: {}", wishlistId);
    }

    @Override
    public List<WishlistDeposit> getDeposits(Long userId, Long wishlistId) {
        log.info("获取存钱记录，userId: {}, wishlistId: {}", userId, wishlistId);

        Wishlist wishlist = wishlistMapper.selectById(wishlistId);
        if (wishlist == null) {
            throw new BusinessException(404, "心愿不存在");
        }

        if (!wishlist.getUserId().equals(userId)) {
            throw new BusinessException(403, "无权查看此记录");
        }

        return wishlistMapper.selectDepositsByWishlistId(wishlistId);
    }

    /**
     * 转换为 VO
     */
    private WishlistVO convertToVO(Wishlist wishlist) {
        WishlistVO vo = new WishlistVO();
        vo.setId(wishlist.getId());
        vo.setUserId(wishlist.getUserId());
        vo.setTitle(wishlist.getTitle());
        vo.setDescription(wishlist.getDescription());
        vo.setTargetAmount(wishlist.getTargetAmount());
        vo.setCurrentAmount(wishlist.getCurrentAmount());
        vo.setProgress(wishlist.getProgress());
        vo.setIcon(wishlist.getIcon());
        vo.setColor(wishlist.getColor());
        vo.setPriority(wishlist.getPriority());
        vo.setPriorityName(wishlist.getPriorityName());
        vo.setStatus(wishlist.getStatus());
        vo.setStatusName(wishlist.getStatusName());
        vo.setTargetDate(wishlist.getTargetDate());
        vo.setCreatedAt(wishlist.getCreatedAt());
        vo.setUpdatedAt(wishlist.getUpdatedAt());
        vo.setCompletedAt(wishlist.getCompletedAt());
        return vo;
    }
}