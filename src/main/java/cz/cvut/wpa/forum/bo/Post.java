package cz.cvut.wpa.forum.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Represents post in the system. Every post must have attached an author
 * @author vlcekmi3
 */
@Entity
public class Post extends AbstractBusinessObject {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition="TEXT")
    private String content;
    @ManyToOne
    private User author;
    @ManyToOne
    private Topic topic;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
        author.addPost(this);
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
        topic.addPost(this);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
