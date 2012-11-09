package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.bo.Message;
import cz.cvut.wpa.forum.bo.User;
import cz.cvut.wpa.forum.dto.MessageDto;
import cz.cvut.wpa.forum.helper.HibernateTools;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author vlcekmi3
 */
@Component
public class MessageServiceImpl extends AbstractDataAccessService implements MessageService {
    
    @Override
    public Long addMessage(String title, String content, Long author) {
        Message newMessage = new Message();
        newMessage.setTitle(title);
        newMessage.setContent(content);
        newMessage.setAuthor(genericDao.loadById(author, User.class));
        
        return genericDao.saveOrUpdate(newMessage).getId();
    }


    @Override
    public void deleteMessage(Long messageId) {
        genericDao.removeById(messageId, Message.class);
    }

    @Override
    public List<MessageDto> getAllMessages() {
        List<Message> messages = genericDao.getAll(Message.class);
        List<MessageDto> messageDtos = new ArrayList<MessageDto>();
        
        for(Message m : messages) {
            messageDtos.add(new MessageDto(m.getId(), m.getTitle(), m.getContent(), HibernateTools.getIdentifier(m.getAuthor())));
        }
        return messageDtos;        
    }
    @Override
    public List<MessageDto> getUsersMessages(Long userId) {
        List<Message> messages = genericDao.getByProperty("author", genericDao.loadById(userId, User.class), Message.class);
        List<MessageDto> messageDtos = new ArrayList<MessageDto>();
        
        for(Message m : messages) {
            messageDtos.add(new MessageDto(m.getId(), m.getTitle(), m.getContent(), HibernateTools.getIdentifier(m.getAuthor())));
        }
        return messageDtos;        
    }

}