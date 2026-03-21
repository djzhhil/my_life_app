package store.scserver.my_life_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.scserver.my_life_app.common.Result;
import store.scserver.my_life_app.entity.Category;
import store.scserver.my_life_app.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类列表
     */
    @Operation(summary = "获取分类列表", description = "根据类型获取分类列表（1:收入, 2:支出）", security = @SecurityRequirement(name = "BearerAuth"))
    @GetMapping("/list")
    public List<Category> list(@RequestParam Integer type, HttpServletRequest request) {
        return categoryService.listByType(type);
    }

    /**
     * 添加分类
     */
    @Operation(summary = "添加分类", description = "创建新的分类", security = @SecurityRequirement(name = "BearerAuth"))
    @PostMapping("/add")
    public Result<Category> add(@RequestBody Category category, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        category.setUserId(userId);
        Category created = categoryService.addCategory(category);
        return Result.success("添加成功", created);
    }

    /**
     * 更新分类
     */
    @Operation(summary = "更新分类", description = "更新分类信息", security = @SecurityRequirement(name = "BearerAuth"))
    @PutMapping("/update/{id}")
    public Result<Category> update(@PathVariable Long id, @RequestBody Category category, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        Category updated = categoryService.updateCategory(id, category, userId);
        return Result.success("更新成功", updated);
    }

    /**
     * 删除分类
     */
    @Operation(summary = "删除分类", description = "删除指定分类", security = @SecurityRequirement(name = "BearerAuth"))
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        categoryService.deleteCategory(id, userId);
        return Result.success("删除成功");
    }
}