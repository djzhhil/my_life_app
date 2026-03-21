package store.scserver.my_life_app.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.scserver.my_life_app.common.Result;
import store.scserver.my_life_app.dto.DepositDTO;
import store.scserver.my_life_app.dto.WishlistCreateDTO;
import store.scserver.my_life_app.dto.WishlistUpdateDTO;
import store.scserver.my_life_app.entity.Wishlist;
import store.scserver.my_life_app.entity.WishlistDeposit;
import store.scserver.my_life_app.service.WishlistService;
import store.scserver.my_life_app.vo.WishlistVO;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    /**
     * 创建心愿
     */
    @PostMapping("/create")
    public Result<Wishlist> create(@Valid @RequestBody WishlistCreateDTO dto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Wishlist wishlist = wishlistService.create(userId, dto);
        return Result.success("创建成功", wishlist);
    }

    /**
     * 获取心愿列表
     */
    @GetMapping("/list")
    public Result<List<WishlistVO>> list(
            @RequestParam(required = false) Integer status,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<WishlistVO> wishlists = wishlistService.list(userId, status);
        return Result.success(wishlists);
    }

    /**
     * 获取心愿详情
     */
    @GetMapping("/{id}")
    public Result<WishlistVO> getDetail(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        WishlistVO wishlist = wishlistService.getDetail(userId, id);
        return Result.success(wishlist);
    }

    /**
     * 存入金币
     */
    @PostMapping("/deposit")
    public Result<Void> deposit(@Valid @RequestBody DepositDTO dto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        wishlistService.deposit(userId, dto);
        return Result.success("存钱成功");
    }

    /**
     * 更新心愿
     */
    @PutMapping("/{id}")
    public Result<Wishlist> update(
            @PathVariable Long id,
            @Valid @RequestBody WishlistUpdateDTO dto,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Wishlist wishlist = wishlistService.update(userId, id, dto);
        return Result.success("更新成功", wishlist);
    }

    /**
     * 删除心愿
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        wishlistService.delete(userId, id);
        return Result.success("删除成功");
    }

    /**
     * 完成心愿
     */
    @PostMapping("/{id}/complete")
    public Result<Void> complete(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        wishlistService.complete(userId, id);
        return Result.success("操作成功");
    }

    /**
     * 放弃心愿
     */
    @PostMapping("/{id}/abandon")
    public Result<Void> abandon(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        wishlistService.abandon(userId, id);
        return Result.success("操作成功");
    }

    /**
     * 获取存钱记录
     */
    @GetMapping("/{id}/deposits")
    public Result<List<WishlistDeposit>> getDeposits(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<WishlistDeposit> deposits = wishlistService.getDeposits(userId, id);
        return Result.success(deposits);
    }
}