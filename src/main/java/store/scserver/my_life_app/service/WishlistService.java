package store.scserver.my_life_app.service;

import store.scserver.my_life_app.dto.DepositDTO;
import store.scserver.my_life_app.dto.WishlistCreateDTO;
import store.scserver.my_life_app.dto.WishlistUpdateDTO;
import store.scserver.my_life_app.entity.Wishlist;
import store.scserver.my_life_app.entity.WishlistDeposit;
import store.scserver.my_life_app.vo.WishlistVO;
import java.math.BigDecimal;
import java.util.List;

public interface WishlistService {

    /**
     * 创建心愿
     */
    Wishlist create(Long userId, WishlistCreateDTO dto);

    /**
     * 获取心愿列表
     */
    List<WishlistVO> list(Long userId, Integer status);

    /**
     * 获取心愿详情
     */
    WishlistVO getDetail(Long userId, Long wishlistId);

    /**
     * 存入金币
     */
    void deposit(Long userId, DepositDTO dto);

    /**
     * 更新心愿
     */
    Wishlist update(Long userId, Long wishlistId, WishlistUpdateDTO dto);

    /**
     * 删除心愿
     */
    void delete(Long userId, Long wishlistId);

    /**
     * 完成心愿
     */
    void complete(Long userId, Long wishlistId);

    /**
     * 放弃心愿
     */
    void abandon(Long userId, Long wishlistId);

    /**
     * 获取存钱记录
     */
    List<WishlistDeposit> getDeposits(Long userId, Long wishlistId);
}