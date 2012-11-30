package cz.cvut.wpa.forum.bb;

import cz.cvut.wpa.forum.dto.UserDto;
import cz.cvut.wpa.forum.service.UserService;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Example of a session scope bean. The user property is set in the user-list.xhtml.
 * Subsequent visits to the user.xhtml page will show the same user until the session 
 * expires or the user clicks on another link in the user-list.xhtml page.
 * @author user
 */
@Component
@Scope(value="session")
public class SelectedUser {
    
    protected UserDto user;

    @Autowired
    protected UserService userService;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
    
    public void setUserById(Long userId) {
        this.user = userService.getUserById(userId);
    }
    
    public String setUserById(String outcome) {
        Long userId = Long.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userid"));
        this.user = userService.getUserById(userId);
        return outcome;
    }
    
}
