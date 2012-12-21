package cz.cvut.wpa.forum.helper;

import cz.cvut.wpa.forum.bo.AbstractBusinessObject;
import cz.cvut.wpa.forum.bo.Message;
import cz.cvut.wpa.forum.bo.Post;
import cz.cvut.wpa.forum.bo.Topic;
import cz.cvut.wpa.forum.bo.User;
import cz.cvut.wpa.forum.dto.MessageDto;
import cz.cvut.wpa.forum.dto.PostDto;
import cz.cvut.wpa.forum.dto.TopicDto;
import cz.cvut.wpa.forum.dto.UserDto;
import org.hibernate.proxy.HibernateProxy;


/**
 * Hibernate support tools
 * @author Pavel Micka
 */
public class HibernateTools {
    
    /**
     * Get id from the proxy of the object without hitting the database
     * @return identifikator objektu
     */
    public static Long getIdentifier(AbstractBusinessObject o){
        if(o == null) return null;
        Long id = null;
        if(o instanceof HibernateProxy){
            id = (Long)((HibernateProxy) o).getHibernateLazyInitializer().getIdentifier();
        }else{
            id = o.getId();
        }
        return id;
    }
    
    public static UserDto getUserDto(User u) {
        if(u == null) return null;
        return new UserDto(u.getId(), u.getUserName(), u.getEmail(), DtoTransformerHelper.getIdentifiers(u.getMessages()), DtoTransformerHelper.getIdentifiers(u.getPosts()), DtoTransformerHelper.getIdentifiers(u.getTopics()), DtoTransformerHelper.getIdentifiers(u.getRoles()), u.getCreated(), u.getUpdated());
    }
    
    public static PostDto getPostDto(Post p) {
        if(p == null) return null;
        return new PostDto(p.getId(), p.getTitle(), p.getContent(), HibernateTools.getUserDto(p.getAuthor()), HibernateTools.getTopicDto(p.getTopic()), p.getCreated(), p.getUpdated());
    }
    
    public static TopicDto getTopicDto(Topic t) {
        if(t == null) return null;
        return new TopicDto(t.getId(), t.getTitle(), getIdentifier(t.getAuthor()), getIdentifier(t.getCategory()), DtoTransformerHelper.getIdentifiers(t.getPosts()), t.getCreated(), t.getUpdated());
    }
    
    public static MessageDto getMessageDto(Message m) {
        if(m == null) return null;
        Long id = null;
        return new MessageDto(m.getId(), m.getTitle(), m.getContent(), getUserDto(m.getAuthor()), getUserDto(m.getRecipient()), m.getCreated(), m.getUpdated());
    }
    
}