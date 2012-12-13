package cz.cvut.wpa.forum.dto;

import java.util.Date;

/**
 *
 * @author vlcekmi3
 */
public class PostDto extends AbstractDto {
    private String title;
    private String content;
    private Long author;
    private UserDto user;
    private Long topic;

    public PostDto() {
    }

    public PostDto(Long id, String title, String content, Long author, Long topic, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.topic = topic;
        this.created = created;
        this.updated = updated;
    }
    
    public PostDto(Long id, String title, String content, Long author, UserDto user, Long topic, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.user = user;
        this.topic = topic;
        this.created = created;
        this.updated = updated;
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