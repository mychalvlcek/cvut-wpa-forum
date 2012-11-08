package cz.cvut.wpa.forum.dto;

/**
 *
 * @author vlcekmi3
 */
public class PostDto extends AbstractDto {
    private String title;
    private Long author;

    public PostDto() {
    }

    public PostDto(Long id, String title, Long author) {
        this.id = id;
        this.title = title;
        this.author = author;
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
    
}