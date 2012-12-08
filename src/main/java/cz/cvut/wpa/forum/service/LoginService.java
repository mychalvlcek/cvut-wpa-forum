package cz.cvut.wpa.forum.service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Login service interface
 *
 * @author vlcekmi3
 */
@Transactional(propagation = Propagation.REQUIRED)
public interface LoginService {

    public void login(ServletRequest req, ServletResponse res);

    public void logout(ServletRequest req, ServletResponse res);
}