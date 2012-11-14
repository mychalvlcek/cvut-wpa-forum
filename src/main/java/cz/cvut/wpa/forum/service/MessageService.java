package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.dto.MessageDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vlcekmi3
 */
@Transactional
public interface MessageService {
    
    /**
     * Add message to the system
     * @param title title of the post
     * @param author id of the author
     * @param recipient id of the recipient
     * @return identifier of the newly added post
     */
    public Long addMessage(String title, String content, Long author, Long recipient);
    /**
     * Deletes message from the system
     * @param postId idenfier of the message to be removed
     */
    public void deleteMessage(Long messageId);
    /**
     * Get all messages stored in the system
     * @return 
     */
    @Transactional(readOnly=true)
    public List<MessageDto> getAllMessages();
    /**
     * Delete all users messages stored in the system
     * @param userId identifier of the user
     */
    public void deleteUsersMessages(Long userId);
    /**
     * Get all messages written by the given user
     * @param userId identifier of the user
     * @return users messages
     */
    @Transactional(readOnly=true)
    public List<MessageDto> getUsersMessages(Long userId);
    
}