package cz.cvut.wpa.forum.bo;

import cz.cvut.wpa.forum.provider.HashProvider;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Entity, which represents user of the system.
 * @author vlcekmi3
 */
@Entity
@Table(name = "users") //user je SQL klicove slovo, nejde ho pouzit pro pojmenovani tabulky
@Configurable(preConstruction=true)
public class User extends AbstractBusinessObject {

    @Column(length = 60, unique = true, nullable = false)
    private String userName; //max 255 chars
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String password;
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String salt;
    @Column(nullable = false)
    private String email;
    private Boolean isAdmin;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Role> roles;
    @OneToMany(mappedBy="author", cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<Post> posts;
    @OneToMany(mappedBy="author", cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
    private List<Topic> topics;
    @OneToMany(mappedBy="author", fetch=FetchType.LAZY)
    @OrderBy("created DESC")
    private List<Message> messages;
    @Autowired
    private transient HashProvider hashProvider; //transient fields are not persisted

    public HashProvider getHashProvider() {
        return hashProvider;
    }

    public void setHashProvider(HashProvider hashProvider) {
        this.hashProvider = hashProvider;
    }
    
    public void addRole(Role role) {
        if(this.roles == null){
            roles = new ArrayList<Role>();
        }
        if(!this.roles.contains(role)) {
            roles.add(role);
        }
    }
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }
    
    /**
     * Add a post to the list of users posts, if not present
     * @param post post to be added
     */
    public void addPost(Post post) {
        if(this.posts == null){
            posts = new ArrayList<Post>();
        }
        if(!this.posts.contains(post)) {
            posts.add(post);
        }
    }
    
    /**
     * Add a topic to the list of users topics, if not present
     * @param topic topic to be added
     */
    public void addTopic(Topic topic) {
        if(this.topics == null){
            topics = new ArrayList<Topic>();
        }
        if(!this.topics.contains(topic)) {
            topics.add(topic);
        }
    }
    
    /**
     * Add a message to the list of users messages, if not present
     * @param message message to be added
     */
    public void addMessage(Message message) {
        if(this.messages == null) {
            messages = new ArrayList<Message>();
        }
        if(!this.messages.contains(message)) {
            messages.add(message);
        }
    }
    
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.salt = hashProvider.computeHash(System.nanoTime() + "");
        this.password = hashProvider.computeHash(password + salt);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean hasPassword(String password) {
        if(hashProvider.computeHash(password + salt).equals(this.password)){
            return true;
        }
        return false;
    }
    
    public Boolean isAdmin() {
        if(isAdmin == null){
            return false;
        }
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}