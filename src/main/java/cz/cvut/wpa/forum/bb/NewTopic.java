package cz.cvut.wpa.forum.bb;

import cz.cvut.wpa.forum.dto.CategoryDto;
import cz.cvut.wpa.forum.dto.PostDto;
import cz.cvut.wpa.forum.dto.TopicDto;
import cz.cvut.wpa.forum.helper.FacesUtil;
import cz.cvut.wpa.forum.service.CategoryService;
import cz.cvut.wpa.forum.service.TopicService;
import cz.cvut.wpa.forum.service.PostService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author vlcekmi3
 */
@Component
@Scope(value="request")
public class NewTopic {
    
    private String title;
    private TopicDto topic;
    private CategoryDto category;
    
    @Autowired
    protected LoggedUser user;
    @Autowired
    protected TopicService topicService;
    @Autowired
    protected CategoryService categoryService;
    
    @PostConstruct
    public void init() throws Exception {
        Long id = null;
        try {
            id = Long.parseLong(FacesUtil.getRequestParameter("id"));
            category = categoryService.getCategoryById(id);
        } catch(Exception e) {
            throw new Exception("Kategorie s id: " + id + " nenalezeno.");
        }
    }
    
    public void storeTopic() {
        Long categoryId = Long.parseLong(FacesUtil.getRequestParameter("category"));
        topicService.addTopic(title, user.id(), categoryId);
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Přispěvek", "Byl úspěšně vložen."));
        //return "category.xhtml?id=5";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TopicDto getTopic() {
        return topic;
    }

    public void setTopic(TopicDto topic) {
        this.topic = topic;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
    
}