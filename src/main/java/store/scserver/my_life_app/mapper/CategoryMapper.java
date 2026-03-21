package store.scserver.my_life_app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import store.scserver.my_life_app.entity.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 插入分类
     */
    int insert(Category category);

    /**
     * 根据ID查询分类
     */
    Category selectById(Long id);

    /**
     * 根据类型查询分类列表
     * @param type 1:收入, 2:支出
     */
    List<Category> selectByType(Integer type);

    /**
     * 更新分类
     */
    int update(Category category);

    /**
     * 删除分类
     */
    int deleteById(Long id);
}