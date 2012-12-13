package cz.cvut.wpa.forum.components;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Extends class userdetails.User from core of spring security.
 * Adding ID identifier to field list.
 * @author vlcekmi3
 */
public class LoggedUserDetails extends User {
    
    private Long id;
    
    public LoggedUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities){
         super(username, password, authorities);
         this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}