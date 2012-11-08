package cz.cvut.wpa.forum.dto;

/**
 *
 * @author vlcekmi3
 */
public class PostDto extends AbstractDto {
    private String title;
    private String content;
    private Long author;
    private Long topic;

    public PostDto() {
    }

    public PostDto(Long id, String title, String content, Long author, Long topic) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.topic = topic;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
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