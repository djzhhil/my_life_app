package store.scserver.my_life_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.scserver.my_life_app.entity.Category;
import store.scserver.my_life_app.exception.BusinessException;
import store.scserver.my_life_app.mapper.CategoryMapper;
import store.scserver.my_life_app.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listByType(String type) {
        return categoryMapper.selectByType(type);
    }

    @Override
    public Category addCategory(Category category) {
        // 验证类型
        if (!"income".equals(category.getType()) && !"expense".equals(category.getType())) {
            throw new BusinessException(400, "类型必须是 income 或 expense");
        }
        categoryMapper.insert(category);
        return category;
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existing = categoryMapper.selectById(id);
        if (existing == null) {
            throw new BusinessException(404, "分类不存在");
        }
        category.setId(id);
        categoryMapper.update(category);
        return categoryMapper.selectById(id);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryMapper.selectById(id);
        if (category == null) {
            throw new BusinessException(404, "分类不存在");
        }
        categoryMapper.deleteById(id);
    }

    @Override
    public Category getById(Long id) {
        return categoryMapper.selectById(id);
    }
}