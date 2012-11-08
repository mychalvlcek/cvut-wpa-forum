package cz.cvut.wpa.forum.dto;

/**
 *
 * @author WolF
 */
public class MessageDto extends AbstractDto {
    private String title;
    private String content;
    private Long author;

    public MessageDto() {
    }

    public MessageDto(Long id, String title, String content, Long author) {
        this.id = id;
        this.title = title;
        this.content = content;
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

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }
    
}
