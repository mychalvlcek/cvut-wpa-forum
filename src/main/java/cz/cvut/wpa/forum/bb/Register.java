package cz.cvut.wpa.forum.bb;

import cz.cvut.wpa.forum.helper.FacesUtil;
import cz.cvut.wpa.forum.service.UserService;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * This bean is used in the registration.xhtml gather form parameter values. 
 * Example of a request scope bean.
 * @author vlcekmi3
 */
@Component
@Scope(value="request")
public class Register {
    
    protected String username;
    protected String email;
    protected String password;
    
    @Autowired
    protected UserService userService;
    
    public String storeUser() {
        userService.addUser(getUsername(), getPassword(), getEmail());
        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Úspěch!", "Registrace proběhla úspěšně."));
        return "index.xhtml";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}