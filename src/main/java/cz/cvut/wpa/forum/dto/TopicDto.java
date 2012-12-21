package cz.cvut.wpa.forum.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author vlcekmi3
 */
public class TopicDto extends AbstractDto {
    private String title;
    private Long author;
    private UserDto user;
    private Long category;
    private List<Long> posts;

    public TopicDto() {
    }

    public TopicDto(Long id, String title, Long author, Long category, List<Long> posts, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.posts = posts;
        this.created = created;
        this.updated = updated;
    }
    
    public TopicDto(Long id, String title, Long author, UserDto user, Long category, List<Long> posts, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.user = user;
        this.category = category;
        this.posts = posts;
        this.created = created;
        this.updated = updated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public List<Long> getPosts() {
        return posts;
    }

    public void setPosts(List<Long> posts) {
        this.posts = posts;
    }
    
    public int getPostsCount() {
        return this.posts.size();
    }
    
}