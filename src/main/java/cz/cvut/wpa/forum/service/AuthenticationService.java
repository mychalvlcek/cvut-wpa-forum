package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.bo.Role;
import cz.cvut.wpa.forum.dao.GenericDao;
import cz.cvut.wpa.forum.helper.FacesUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import cz.cvut.wpa.forum.components.LoggedUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Authentication provider pro Flexiblu 2
 * @author Pavel Micka
 */
//Configuration in applicationContext-security.xml
public class AuthenticationService extends AbstractUserDetailsAuthenticationProvider {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractUserDetailsAuthenticationProvider.class);
    private GenericDao genericDAO;
    private TransactionTemplate transactionTemplate;

    public AuthenticationService() {
        this.setUserCache(new NullUserCache());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails ud, UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        // do nothing
    }

    /**
     * Krome specifikace z nadtridy prida v pripade uspesneho prihlaseni do sessionHolderu pod klic "user" daneho uzivatele
     * @param username
     * @param upat
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        //only public methods can be marked as transactional
        return (UserDetails) transactionTemplate.execute(new TransactionCallback() {

            @Override
            public Object doInTransaction(TransactionStatus status) { 
                UserDetails ud = null;
                
                try {
                    cz.cvut.wpa.forum.bo.User u = genericDAO.getByPropertyUnique("username", username, cz.cvut.wpa.forum.bo.User.class);
                    String password = (String) upat.getCredentials();
                    if (u == null || !u.hasPassword(password)) {
                        throw new BadCredentialsException("Neplatne uzivatelske udaje!");
                    } else {
                        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
                        for(Role role : u.getRoles()) {
                            auths.add(new GrantedAuthorityImpl(role.getName()));
                        }
                        ud = new LoggedUserDetails(u.getId(), u.getUserName(), u.getPassword(), auths);
                    }
                } catch(AuthenticationException e) {
                    status.setRollbackOnly();
                    throw e;
                }catch (Exception e) {
                    LOG.error("Error occured during retrieveUser call", e);
                    status.setRollbackOnly();
                    throw new BadCredentialsException("Neplatné uživatelské údaje!");
                }
                return ud;
            }
        });
    }

    public void setGenericDAO(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
       
}