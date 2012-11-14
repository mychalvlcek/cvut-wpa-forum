package cz.cvut.wpa.forum.bo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Represents topic in the system. Every topic must have attached an author
 * @author vlcekmi3
 */
@Entity
public class Topic extends AbstractBusinessObject {
    @Column(nullable = false)
    private String title;
    @ManyToOne
    private User author;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy="topic", cascade=CascadeType.REMOVE)
    private List<Post> posts;

    /**
     * Add a post to the topic, if not present
     * @param post post to be added
     */
    public void addPost(Post post){
        if(this.posts == null){
            posts = new ArrayList<Post>();
        }
        if(!this.posts.contains(post)){
            posts.add(post);
        }
    }
    
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
        author.addTopic(this);
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    
}