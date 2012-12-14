package cz.cvut.wpa.forum.bb;

import cz.cvut.wpa.forum.dto.CategoryDto;
import cz.cvut.wpa.forum.helper.FacesUtil;
import cz.cvut.wpa.forum.service.CategoryService;
import cz.cvut.wpa.forum.service.TopicService;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    
    private Long categoryId;
    private CategoryDto category;
    
    private String title;
    
    @Autowired
    protected LoggedUser user;
    @Autowired
    protected TopicService topicService;
    @Autowired
    protected CategoryService categoryService;
    
    public void init() throws Exception {
        try {
            category = categoryService.getCategoryById(categoryId);
        } catch(Exception e) {
            throw new Exception("Kategorie s id: " + categoryId + " nenalezena.");
        }
    }
    
    public void storeTopic() throws IOException {
        categoryId = Long.parseLong(FacesUtil.getRequestParameter("category"));
        Long newTopicId = topicService.addTopic(title, user.id(), categoryId);
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Topic", "Byl úspěšně vložen."));
        FacesContext.getCurrentInstance().getExternalContext().redirect("topic.xhtml?id=" + newTopicId + "&new=1");
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
    
}