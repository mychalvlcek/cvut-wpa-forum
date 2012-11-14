package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.dto.CategoryDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vlcekmi3
 */
@Transactional
public interface CategoryService {

    /**
     * Add category to the system
     * @param title title
     * @return identifier of the category stored
     */
    public Long addCategory(String title);

    /**
     * Permanently removes the category
     * @param categoryId id of the category to be removed
     */
    public void deleteCategory(Long categoryId);
    /**
     * Return category with the given id
     * @param id idenfier of the category to be retrieved
     * @return category with the given id, null if the category does not exist
     */
    @Transactional(readOnly=true)
    public CategoryDto getCategoryById(Long id);

    /**
     * Get all categories stored in the system
     * @return 
     */
    @Transactional(readOnly=true)
    public List<CategoryDto> getAllCategories();
    
}