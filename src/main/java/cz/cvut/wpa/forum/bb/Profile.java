package cz.cvut.wpa.forum.bb;

import cz.cvut.wpa.forum.dto.MessageDto;
import cz.cvut.wpa.forum.dto.PostDto;
import cz.cvut.wpa.forum.dto.UserDto;
import cz.cvut.wpa.forum.helper.FacesUtil;
import cz.cvut.wpa.forum.service.MessageService;
import cz.cvut.wpa.forum.service.PostService;
import cz.cvut.wpa.forum.service.UserService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * @author vlcekmi3
 */
@Component
@Scope(value="request")
public class Profile {
    
    private UserDto user;
    
    @Autowired
    protected UserService userService;
    @Autowired
    protected MessageService messageService;
    @Autowired
    protected PostService postService;

    @PostConstruct
    public void initUser() throws Exception {
        Long id = null;
        try {
            id = Long.parseLong(FacesUtil.getRequestParameter("id"));
            user = userService.getUserById(id);
        } catch(Exception e) {
            throw new Exception("Uzivatel s id: " + id + " nenalezen.");
        }
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
    
    /**
     * Returns users messages
     * @return list of messages
     */
    public List<MessageDto> getMessages() {
        return messageService.getUsersMessages(user.getId());
    }
    
    /**
     * Returns users posts
     * @return list of posts
     */
    public List<PostDto> getPosts() {
        return postService.getUsersPosts(user.getId());
    }
    
    /**
     * Returns number of days since registration
     * @return number of days
     */
    public int days() {
        long now = new Date().getTime();
        long register = user.getCreated().getTime();
        long diffTime = now - register;
        return (int) diffTime / (1000 * 60 * 60 * 24) + 1;
    }
    
}