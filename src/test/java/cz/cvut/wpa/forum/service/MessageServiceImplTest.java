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
        Long userId = addUser();
        Long recipientId = addUser();

        String title = "Nadpis";
        String content = "Obsah";

        Long postId = messageService.addMessage(title, content, userId, recipientId);
        messageService.addMessage(title, content, recipientId, userId);
        List<MessageDto> messages = messageService.getUsersMessages(userId);
        assertEquals(1, messages.size());

        MessageDto message = messages.get(0);

        assertEquals(title, message.getTitle());
        assertEquals(userId, message.getAuthor());
        assertEquals(recipientId, message.getRecipient());
        assertEquals(postId, message.getId());
    }

    @Test
    public void testAddAndRemoveMessage() {
        Long userId = addUser();
        Long recipientId = addUser();

        String title = "Nadpis";
        String content = "Obsah";
        
        Long messageId = messageService.addMessage(title, content, userId, recipientId);
        Long message2Id = messageService.addMessage(title, content, userId, recipientId);
        Long message3Id = messageService.addMessage(title, content, recipientId, userId);
        assertEquals(3, messageService.getAllMessages().size());
        messageService.deleteUsersMessages(userId);
        //assertEquals(1, messageService.getAllMessages().size());
        messageService.deleteMessage(message3Id);
        //assertEquals(0, messageService.getAllMessages().size());
    }
    
    @Test
    public void testMessageDeletedWhenUserRemoved() {
        Long userId = addUser();
        Long recipientId = addUser();

        String title = "Nadpis";
        String content = "Obsah";

        messageService.addMessage(title, content, userId, recipientId);
        assertEquals(1, messageService.getAllMessages().size());
        
        userService.deleteUser(userId);
        //assertEquals(0, messageService.getAllMessages().size());
        
        userService.deleteUser(recipientId);
        
        //assertEquals(0, messageService.getAllMessages().size());
    }
    
    @Test
    public void testUserDeletedWhenMessageRemoved() {
        Long userId = addUser();
        Long recipientId = addUser();

        String title = "Nadpis";
        String content = "Obsah";

        Long id = messageService.addMessage(title, content, userId, recipientId);
        assertEquals(1, messageService.getAllMessages().size());
        
        messageService.deleteMessage(id);
        assertEquals(2, userService.getAllUsers().size());
    }
    
    private long addUser() {
        String userName = "UserName" + System.currentTimeMillis();
        String passwd = "passwd" + System.currentTimeMillis();
        String email = "email@email.com";

        return userService.addUser(userName, passwd, email);
    }

}