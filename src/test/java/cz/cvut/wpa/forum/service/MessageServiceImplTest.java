package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.dto.MessageDto;
import cz.cvut.wpa.forum.service.MessageService;
import cz.cvut.wpa.forum.service.UserService;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author vlcekmi3
 */
public class MessageServiceImplTest extends AbstractServiceTest {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    public MessageServiceImplTest() {
        super();
    }

    @Test
    public void testAddAndRetrieveMessage() {

    }

    @Test
    public void testAddAndRemoveMessage() {

    }
    
    @Test
    public void testMessageDeletedWhenUserRemoved() {
        Long userId = addUser();

        String title = "Nadpis";
        String content = "Obsah";

        messageService.addMessage(title, content, userId);
        assertEquals(1, messageService.getAllMessages().size());
        
        userService.deleteUser(userId);    
        assertEquals(0, messageService.getAllMessages().size());
    }
    
    @Test
    public void testUserDeletedWhenMessageRemoved() {
        Long userId = addUser();

        String title = "Nadpis";
        String content = "Obsah";

        Long id = messageService.addMessage(title, content, userId);
        assertEquals(1, messageService.getAllMessages().size());
        
        messageService.deleteMessage(id);
        assertEquals(1, userService.getAllUsers().size());
    }
    
    private long addUser() {
        String userName = "UserName" + System.currentTimeMillis();
        String passwd = "passwd" + System.currentTimeMillis();
        String email = "email@email.com";

        return userService.addUser(userName, passwd, email);
    }

}