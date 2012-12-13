package cz.cvut.wpa.forum.bb;

import cz.cvut.wpa.forum.helper.FacesUtil;
import cz.cvut.wpa.forum.service.PostService;
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
public class Post {
    
    private String title;
    private String content;
    
    private Long topic;
    
    @Autowired
    protected LoggedUser user;
    @Autowired
    protected PostService postService;
    
    public void storePost() throws IOException {
        //Long topicId = Long.parseLong(FacesUtil.getRequestParameter("id"));
        String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("topic");
        postService.addPost(title, content, user.id(), Long.parseLong(value));
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Přispěvek", "Byl úspěšně vložen."));
        FacesContext.getCurrentInstance().getExternalContext().redirect("topic.xhtml?id=13");
        //return "topic.xhtml?id=13";
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

    public Long getTopic() {
        return topic;
    }

    public void setTopic(Long topic) {
        this.topic = topic;
    }
    
}