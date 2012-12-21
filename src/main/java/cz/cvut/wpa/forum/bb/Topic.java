package cz.cvut.wpa.forum.bb;

import cz.cvut.wpa.forum.dto.CategoryDto;
import cz.cvut.wpa.forum.dto.PostDto;
import cz.cvut.wpa.forum.dto.TopicDto;
import cz.cvut.wpa.forum.helper.FacesUtil;
import cz.cvut.wpa.forum.service.CategoryService;
import cz.cvut.wpa.forum.service.TopicService;
import cz.cvut.wpa.forum.service.PostService;
import java.io.IOException;
import java.util.List;
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
@Scope(value="view")
public class Topic {
    
    private Long topicId;
    private TopicDto topic;
    
    private String title;
    private String content;
    
    @Autowired
    protected LoggedUser user;
    @Autowired
    protected CategoryService categoryService;
    @Autowired
    protected PostService postService;
    @Autowired
    protected TopicService topicService;

    public void init() throws Exception {
        try {
            topic = topicService.getTopicById(topicId);
        } catch(Exception e) {
            throw new Exception("Tema s id: " + topicId + " nenalezeno.");
        }
    }
    
    public void storePost() throws IOException {
        topicId = Long.parseLong(FacesUtil.getRequestParameter("topic"));
        postService.addPost(title, content, user.id(), topicId);
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Topic", "Byl úspěšně vložen."));
        FacesContext.getCurrentInstance().getExternalContext().redirect("topic.xhtml?id=" + topicId + "&new=1");
    }
    
    public void deletePost(Long id) throws IOException {
        postService.deletePost(id);
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Příspěvek", "Byl smazán."));
        FacesContext.getCurrentInstance().getExternalContext().redirect("topic.xhtml?id=" + topicId + "&del=1");
    }

    public CategoryDto getCategory() {
        return categoryService.getCategoryById(topic.getCategory());
    }
    
    public List<PostDto> getPosts() {
        return postService.getPostsByTopic(topicId);
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long id) {
        this.topicId = id;
    }

    public TopicDto getTopic() {
        return topic;
    }

    public void setTopic(TopicDto topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}