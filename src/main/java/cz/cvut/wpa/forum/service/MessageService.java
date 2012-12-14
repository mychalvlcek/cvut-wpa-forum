package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.dto.MessageDto;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
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
    //@PreAuthorize("hasRole('ROLE_USER')")
    public Long addMessage(String title, String content, Long author, Long recipient);
    /**
     * Deletes message from the system
     * @param postId idenfier of the message to be removed
     */
    @PreAuthorize("hasRole('ROLE_USER')")
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteUsersMessages(Long userId);
    /**
     * Get all messages written by the given user
     * @param userId identifier of the user
     * @return users messages
     */
    //@PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(readOnly=true)
    public List<MessageDto> getUsersMessages(Long userId);
    
}