package store.scserver.my_life_app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import store.scserver.my_life_app.entity.Wishlist;
import store.scserver.my_life_app.entity.WishlistDeposit;
import java.util.List;

@Mapper
public interface WishlistMapper {

    /**
     * 根据用户ID查询心愿列表
     */
    List<Wishlist> selectByUserId(@Param("userId") Long userId, @Param("status") Integer status);

    /**
     * 根据ID查询心愿
     */
    Wishlist selectById(@Param("id") Long id);

    /**
     * 插入心愿
     */
    void insert(Wishlist wishlist);

    /**
     * 更新心愿
     */
    void update(Wishlist wishlist);

    /**
     * 删除心愿
     */
    void deleteById(@Param("id") Long id);

    /**
     * 查询心愿的存钱记录
     */
    List<WishlistDeposit> selectDepositsByWishlistId(@Param("wishlistId") Long wishlistId);

    /**
     * 插入存钱记录
     */
    void insertDeposit(WishlistDeposit deposit);

    /**
     * 更新心愿当前金额
     */
    void updateAmount(@Param("id") Long id, @Param("amount") java.math.BigDecimal amount,
                      @Param("status") Integer status, @Param("completedAt") java.time.LocalDateTime completedAt);

    /**
     * 统计用户心愿数量
     */
    Integer countByUserId(@Param("userId") Long userId);
}
