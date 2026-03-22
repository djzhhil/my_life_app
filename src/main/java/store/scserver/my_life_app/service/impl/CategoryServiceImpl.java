package store.scserver.my_life_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.scserver.my_life_app.entity.Category;
import store.scserver.my_life_app.exception.BusinessException;
import store.scserver.my_life_app.mapper.CategoryMapper;
import store.scserver.my_life_app.service.CategoryService;
import store.scserver.my_life_app.util.UserContext;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listByType(Integer type) {
        Long userId = UserContext.getCurrentUserId();
        return categoryMapper.selectByUserIdAndType(userId, type);
    }

    @Override
    public Category addCategory(Category category) {
        // 验证类型：1=收入, 2=支出
        if (category.getType() == null || (category.getType() != 1 && category.getType() != 2)) {
            throw new BusinessException(400, "类型必须是 1（收入）或 2（支出）");
        }
        Long userId = UserContext.getCurrentUserId();
        category.setUserId(userId);
        categoryMapper.insert(category);
        return category;
    }

    @Override
    public Category updateCategory(Long id, Category category, Long userId) {
        Category existing = categoryMapper.selectById(id);
        if (existing == null) {
            throw new BusinessException(404, "分类不存在");
        }
        // 验证类型
        if (category.getType() != null && category.getType() != 1 && category.getType() != 2) {
            throw new BusinessException(400, "类型必须是 1（收入）或 2（支出）");
        }
        category.setId(id);
        category.setUserId(userId);
        categoryMapper.update(category);
        return categoryMapper.selectById(id);
    }

    @Override
    public void deleteCategory(Long id, Long userId) {
        Category category = categoryMapper.selectById(id);
        if (category == null) {
            throw new BusinessException(404, "分类不存在");
        }
        // TODO: 检查是否有关联的交易记录
        categoryMapper.deleteById(id);
    }

    @Override
    public Category getById(Long id) {
        return categoryMapper.selectById(id);
    }
}