/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.wpa.forum.dto;
import java.util.List;

/**
 *
 * @author vlcekmi3
 */
public class UserDto extends AbstractDto{
    private String userName;
    private String email;
    private List<Long> posts;

    public UserDto() {
    }

    public UserDto(Long id, String userName, String email, List<Long> posts) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.posts = posts;
    }

    public List<Long> getPosts() {
        return posts;
    }

    public void setPosts(List<Long> posts) {
        this.posts = posts;
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
    
}
