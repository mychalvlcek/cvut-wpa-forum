package cz.cvut.wpa.forum.bb;

import cz.cvut.wpa.forum.components.LoggedUserDetails;
import cz.cvut.wpa.forum.helper.FacesUtil;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Example of a session scope bean. The user property is set in the user-list.xhtml.
 * Subsequent visits to the user.xhtml page will show the same user until the session 
 * expires or the user clicks on another link in the user-list.xhtml page.
 * @author vlcekmi3
 */
@Component
@Scope(value="session")
public class LoggedUser {
    
    public Long id() {
        LoggedUserDetails user = (LoggedUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
    
    public String username() {
        LoggedUserDetails user = (LoggedUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
    
}
