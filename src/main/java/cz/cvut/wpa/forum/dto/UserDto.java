package cz.cvut.wpa.forum.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author vlcekmi3
 */
public class UserDto extends AbstractDto{
    private String userName;
    private String email;
    private List<Long> messages;
    private List<Long> posts;
    private List<Long> topics;
    private List<Long> roles;

    public UserDto() {
    }

    public UserDto(Long id, String userName, String email, List<Long> messages, List<Long> posts, List<Long> topics, List<Long> roles, Date created, Date updated) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.messages = messages;
        this.posts = posts;
        this.topics = topics;
        this.roles = roles;
        this.created = created;
        this.updated = updated;
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

    public List<Long> getMessages() {
        return messages;
    }

    public void setMessages(List<Long> messages) {
        this.messages = messages;
    }

    public List<Long> getPosts() {
        return posts;
    }

    public void setPosts(List<Long> posts) {
        this.posts = posts;
    }

    public List<Long> getTopics() {
        return topics;
    }

    public void setTopics(List<Long> topics) {
        this.topics = topics;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }
    
    public int getPostsCount() {
        return this.posts.size();
    }
    
}