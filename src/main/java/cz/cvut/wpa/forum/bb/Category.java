package cz.cvut.wpa.forum.bb;

import cz.cvut.wpa.forum.dto.CategoryDto;
import cz.cvut.wpa.forum.dto.TopicDto;
import cz.cvut.wpa.forum.service.CategoryService;
import cz.cvut.wpa.forum.service.TopicService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author vlcekmi3
 */
@Component
@Scope(value="request")
public class Category {
    
    private Long id;
    private CategoryDto category;
    
    @Autowired
    protected CategoryService categoryService;
    @Autowired
    protected TopicService topicService;

    public void init() throws Exception {
        try {
            category = categoryService.getCategoryById(id);
        } catch(Exception e) {
            throw new Exception("Kategorie s id: " + id + " nenalezena.");
        }
    }
    
    public List<TopicDto> getTopics() {
        return topicService.getTopicsByCategory(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
    
}