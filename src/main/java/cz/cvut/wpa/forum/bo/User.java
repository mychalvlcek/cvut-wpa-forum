package cz.cvut.wpa.forum.bo;

import cz.cvut.wpa.forum.provider.HashProvider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Entity, which represents user of the system.
 * User: username, password, email
 * @author vlcekmi3
 */
@Entity
@Table(name = "users") //user je SQL klicove slovo, nejde ho pouzit po pojmenovani tabulky
@Configurable(preConstruction=true)
public class User extends AbstractBusinessObject {

    @Column(length = 255, unique = true, nullable = false)
    private String userName; //max 255 chars
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String password;
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String salt;
    @Column(nullable = false)
    private String email;
    @ManyToMany
    private Set<Role> roles = new HashSet<Role>();
    @OneToMany(mappedBy="author", cascade=CascadeType.REMOVE)
    private List<Post> posts;
    @Autowired
    private transient HashProvider hashProvider; //transient fields are not persisted

    public HashProvider getHashProvider() {
        return hashProvider;
    }

    public void setHashProvider(HashProvider hashProvider) {
        this.hashProvider = hashProvider;
    }
    
    public boolean addRole(Role role) {
        return roles.add(role);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    /**
     * Add a post to the list of users posts, if not present
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
}
