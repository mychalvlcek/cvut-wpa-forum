package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.bo.Category;
import cz.cvut.wpa.forum.dto.CategoryDto;
import cz.cvut.wpa.forum.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author vlcekmi3
 */
@Component
public class CategoryServiceImpl extends AbstractDataAccessService implements CategoryService {

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = genericDao.getAll(Category.class);
        List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>();

        for (Category c : categories) {
            categoryDtos.add(new CategoryDto(c.getId(), c.getTitle(),DtoTransformerHelper.getIdentifiers(c.getTopics())));
        }
        return categoryDtos;
    }

    @Override
    public Long addCategory(String title) {
        Category newCategory = new Category();
        newCategory.setTitle(title);
        
        return genericDao.saveOrUpdate(newCategory).getId();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        genericDao.removeById(categoryId, Category.class);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category c = genericDao.getByPropertyUnique("id", id, Category.class);
        return new CategoryDto(c.getId(), c.getTitle(), DtoTransformerHelper.getIdentifiers(c.getTopics()));
    }
}
