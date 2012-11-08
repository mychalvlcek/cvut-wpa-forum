package cz.cvut.wpa.forum.dto;

import java.util.List;

/**
 *
 * @author vlcekmi3
 */
public class TopicDto extends AbstractDto {
    private String title;
    private Long author;
    private Long category;
    private List<Long> posts;

    public TopicDto() {
    }

    public TopicDto(Long id, String title, Long author, Long category, List<Long> posts) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.posts = posts;
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

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }
    
}
