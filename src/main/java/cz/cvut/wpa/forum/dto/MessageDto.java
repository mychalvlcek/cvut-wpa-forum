package cz.cvut.wpa.forum.dto;

import java.util.Date;

/**
 *
 * @author vlcekmi3
 */
public class MessageDto extends AbstractDto {
    private String title;
    private String content;
    private UserDto author;
    private UserDto recipient;

    public MessageDto() {
    }

    public MessageDto(Long id, String title, String content, UserDto author, UserDto recipient, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.recipient = recipient;
        this.created = created;
        this.updated = updated;
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

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
    }

    public UserDto getRecipient() {
        return recipient;
    }

    public void setRecipient(UserDto recipient) {
        this.recipient = recipient;
    }
    
}