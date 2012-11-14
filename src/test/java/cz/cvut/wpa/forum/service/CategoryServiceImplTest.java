package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.dto.CategoryDto;
import cz.cvut.wpa.forum.service.CategoryService;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author vlcekmi3
 */
public class CategoryServiceImplTest extends AbstractServiceTest {
    @Autowired
    private CategoryService categoryService;

    public CategoryServiceImplTest() {
        super();
    }

    @Test
    public void testAddAndRetrieveCategory() {
        String title = "Cat";
        Long id = categoryService.addCategory(title);
        List<CategoryDto> categories = categoryService.getAllCategories();
        assertEquals(1, categories.size());
        CategoryDto category = categoryService.getCategoryById(id);

        assertEquals(title, category.getTitle());
    }

    @Test
    public void testAddAndRemoveCategory() {
        String title = "Cat";
        Long categoryId = categoryService.addCategory(title);
        List<CategoryDto> categories = categoryService.getAllCategories();
        
        assertEquals(1, categoryService.getAllCategories().size());
        categoryService.deleteCategory(categoryId);
        assertEquals(0, categoryService.getAllCategories().size());
    }

}