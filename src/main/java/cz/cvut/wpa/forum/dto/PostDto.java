package cz.cvut.wpa.forum.dto;

import java.util.Date;

/**
 *
 * @author vlcekmi3
 */
public class PostDto extends AbstractDto {
    private String title;
    private String content;
    private UserDto author;
    private TopicDto topic;

    public PostDto() {
    }

    public PostDto(Long id, String title, String content, UserDto author, TopicDto topic, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.topic = topic;
        this.created = created;
        this.updated = updated;
    }

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
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