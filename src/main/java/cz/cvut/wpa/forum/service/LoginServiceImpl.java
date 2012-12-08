package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.service.LoginService;
import cz.cvut.wpa.forum.helper.FacesUtil;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

/**
 * Login service
 *
 * @author vlcekmi3
 */
@Component
public class LoginServiceImpl implements LoginService {

    public void login(ServletRequest req, ServletResponse res) {
        RequestDispatcher dispatcher = ((ServletRequest) req).getRequestDispatcher("/j_spring_security_check");
        try {
            dispatcher.forward(req, res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void logout(ServletRequest req, ServletResponse res) {
        try {
            RequestDispatcher dispatcher = (req).getRequestDispatcher("/j_spring_security_logout");
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
            dispatcher.forward((ServletRequest) req, (ServletResponse) res);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}