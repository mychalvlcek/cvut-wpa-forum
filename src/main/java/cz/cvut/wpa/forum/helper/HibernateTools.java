package cz.cvut.wpa.forum.helper;

import cz.cvut.wpa.forum.bo.AbstractBusinessObject;
import cz.cvut.wpa.forum.bo.User;
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
        Long id = null;
        return new UserDto(u.getId(), u.getUserName(), u.getEmail(), DtoTransformerHelper.getIdentifiers(u.getMessages()), DtoTransformerHelper.getIdentifiers(u.getPosts()), DtoTransformerHelper.getIdentifiers(u.getTopics()), DtoTransformerHelper.getIdentifiers(u.getRoles()), u.getCreated(), u.getUpdated());
    }
    
}