package store.scserver.my_life_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.scserver.my_life_app.common.Result;
import store.scserver.my_life_app.entity.Category;
import store.scserver.my_life_app.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类列表
     */
    @GetMapping("/list")
    public List<Category> list(@RequestParam String type) {
        return categoryService.listByType(type);
    }

    /**
     * 添加分类
     */
    @PostMapping("/add")
    public Result<Category> add(@RequestBody Category category) {
        Category created = categoryService.addCategory(category);
        return Result.success("添加成功", created);
    }

    /**
     * 更新分类
     */
    @PutMapping("/update/{id}")
    public Result<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Category updated = categoryService.updateCategory(id, category);
        return Result.success("更新成功", updated);
    }

    /**
     * 删除分类
     */
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return Result.success("删除成功");
    }
}