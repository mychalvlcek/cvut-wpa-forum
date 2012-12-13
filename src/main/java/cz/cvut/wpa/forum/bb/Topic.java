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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author vlcekmi3
 */
@Component
@Scope(value="request")
public class Topic {
    
    private TopicDto topic;
    
    @Autowired
    protected CategoryService categoryService;
    @Autowired
    protected PostService postService;
    @Autowired
    protected TopicService topicService;

    @PostConstruct
    public void initPost() throws Exception {
        Long id = null;
        try {
            id = Long.parseLong(FacesUtil.getRequestParameter("id"));
            topic = topicService.getTopicById(id);
        } catch(Exception e) {
            throw new Exception("Tema s id: " + id + " nenalezeno.");
        }
    }
    
    public CategoryDto getCategory() {
        return categoryService.getCategoryById(topic.getCategory());
    }
    
    public List<PostDto> getPosts() {
        return postService.getPostsByTopic(topic.getId());
    }

    public TopicDto getTopic() {
        return topic;
    }

    public void setTopic(TopicDto topic) {
        this.topic = topic;
    }
    
}