package cz.cvut.wpa.forum.helper;

import cz.cvut.wpa.forum.bo.AbstractBusinessObject;
import cz.cvut.wpa.forum.bo.Topic;
import cz.cvut.wpa.forum.dto.AbstractDto;
import cz.cvut.wpa.forum.dto.TopicDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mickapa1
 */
public class DtoTransformerHelper {
    
    /**
     * Convert list of entities to list of identifiers
     * @param list list to be converted
     * @return list of identifiers, null - if list is null
     */
    public static List<Long> getIdentifiers(List<? extends AbstractBusinessObject> list) {
        if (list == null) {
            return null;
        }
        List<Long> ids = new ArrayList<Long>();

        for (AbstractBusinessObject abo : list) {
            ids.add(abo.getId());
        }
        return ids;
    }
    
    public static List<TopicDto> getTopicDtos(List<? extends Topic> list) {
        if (list == null) {
            return null;
        }
        List<TopicDto> topicDtos = new ArrayList<TopicDto>();
        for (Topic t : list) {
            topicDtos.add(new TopicDto(t.getId(), t.getTitle(), HibernateTools.getIdentifier(t.getAuthor()), HibernateTools.getIdentifier(t.getCategory()) ,DtoTransformerHelper.getIdentifiers(t.getPosts()), t.getCreated(), t.getUpdated()));
        }
        return topicDtos;
    }
    
}