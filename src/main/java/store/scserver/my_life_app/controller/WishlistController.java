package store.scserver.my_life_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(summary = "创建心愿", description = "创建新的心愿目标", security = @SecurityRequirement(name = "BearerAuth"))
    @PostMapping("/create")
    public Result<Wishlist> create(@Valid @RequestBody WishlistCreateDTO dto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Wishlist wishlist = wishlistService.create(userId, dto);
        return Result.success("创建成功", wishlist);
    }

    /**
     * 获取心愿列表
     */
    @Operation(summary = "获取心愿列表", description = "获取用户的心愿列表，支持按状态筛选（0:进行中, 1:已实现, 2:已放弃）", security = @SecurityRequirement(name = "BearerAuth"))
    @GetMapping("/list")
    public Result<List<WishlistVO>> list(
            @io.swagger.v3.oas.annotations.Parameter(description = "状态筛选（可选）") @RequestParam(required = false) Integer status,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<WishlistVO> wishlists = wishlistService.list(userId, status);
        return Result.success(wishlists);
    }

    /**
     * 获取心愿详情
     */
    @Operation(summary = "获取心愿详情", description = "获取指定心愿的详细信息", security = @SecurityRequirement(name = "BearerAuth"))
    @GetMapping("/{id}")
    public Result<WishlistVO> getDetail(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        WishlistVO wishlist = wishlistService.getDetail(userId, id);
        return Result.success(wishlist);
    }

    /**
     * 存入金币
     */
    @Operation(summary = "存入金币", description = "向心愿存入金币，会扣除用户金币并更新进度", security = @SecurityRequirement(name = "BearerAuth"))
    @PostMapping("/deposit")
    public Result<Void> deposit(@Valid @RequestBody DepositDTO dto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        wishlistService.deposit(userId, dto);
        return Result.success("存钱成功");
    }

    /**
     * 更新心愿
     */
    @Operation(summary = "更新心愿", description = "更新心愿信息（已实现的不可修改）", security = @SecurityRequirement(name = "BearerAuth"))
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
    @Operation(summary = "删除心愿", description = "删除指定心愿", security = @SecurityRequirement(name = "BearerAuth"))
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        wishlistService.delete(userId, id);
        return Result.success("删除成功");
    }

    /**
     * 完成心愿
     */
    @Operation(summary = "完成心愿", description = "手动标记心愿为已完成", security = @SecurityRequirement(name = "BearerAuth"))
    @PostMapping("/{id}/complete")
    public Result<Void> complete(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        wishlistService.complete(userId, id);
        return Result.success("操作成功");
    }

    /**
     * 放弃心愿
     */
    @Operation(summary = "放弃心愿", description = "放弃心愿，会将已存金额退回用户", security = @SecurityRequirement(name = "BearerAuth"))
    @PostMapping("/{id}/abandon")
    public Result<Void> abandon(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        wishlistService.abandon(userId, id);
        return Result.success("操作成功");
    }

    /**
     * 获取存钱记录
     */
    @Operation(summary = "获取存钱记录", description = "获取指定心愿的存钱历史记录", security = @SecurityRequirement(name = "BearerAuth"))
    @GetMapping("/{id}/deposits")
    public Result<List<WishlistDeposit>> getDeposits(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<WishlistDeposit> deposits = wishlistService.getDeposits(userId, id);
        return Result.success(deposits);
    }
}