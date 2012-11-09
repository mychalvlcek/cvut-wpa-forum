package cz.cvut.wpa.forum.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Represents private message in the system.
 * @author vlcekmi3
 */
@Entity
public class Message extends AbstractBusinessObject {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition="TEXT")
    private String content;
    @ManyToOne
    private User author;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
        author.addMessage(this);
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
