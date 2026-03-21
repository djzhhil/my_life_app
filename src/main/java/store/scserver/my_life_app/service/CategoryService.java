package store.scserver.my_life_app.service;

import store.scserver.my_life_app.entity.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 根据类型获取分类列表
     */
    List<Category> listByType(String type);

    /**
     * 添加分类
     */
    Category addCategory(Category category);

    /**
     * 更新分类
     */
    Category updateCategory(Long id, Category category);

    /**
     * 删除分类
     */
    void deleteCategory(Long id);

    /**
     * 根据ID获取分类
     */
    Category getById(Long id);
}