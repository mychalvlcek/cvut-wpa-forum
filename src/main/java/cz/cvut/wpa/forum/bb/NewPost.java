package cz.cvut.wpa.forum.bb;

import cz.cvut.wpa.forum.dto.PostDto;
import cz.cvut.wpa.forum.helper.FacesUtil;
import cz.cvut.wpa.forum.service.PostService;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Edit post backing bean
 * @author vlcekmi3
 */
@Component
@Scope(value="view")
public class NewPost {
    
    private Long id;
    private String title;
    private String content;
    private Long topicId;
    
    private PostDto post;
    
    @Autowired
    protected LoggedUser user;
    @Autowired
    protected PostService postService;
    
    public void init() throws Exception {
        try {
            post = postService.getPostById(id);
            this.title = post.getTitle();
            this.content = post.getContent();
            this.topicId = post.getTopic().getId();
        } catch(Exception e) {
            throw new Exception("Příspěvek s id: " + id + " nenalezen.");
        }
    }
    
    public void storePost() throws IOException {
        postService.updatePost(id, title, content, post.getAuthor().getId(), topicId);
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Post", "Byl úspěšně upraven."));
        FacesContext.getCurrentInstance().getExternalContext().redirect("topic.xhtml?id=" + topicId + "&update=1");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }
    
}