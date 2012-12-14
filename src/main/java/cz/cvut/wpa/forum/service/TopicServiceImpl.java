package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.bo.Category;
import cz.cvut.wpa.forum.bo.Post;
import cz.cvut.wpa.forum.bo.Topic;
import cz.cvut.wpa.forum.bo.User;
import cz.cvut.wpa.forum.dto.PostDto;
import cz.cvut.wpa.forum.dto.TopicDto;
import cz.cvut.wpa.forum.helper.DtoTransformerHelper;
import cz.cvut.wpa.forum.helper.HibernateTools;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author vlcekmi3
 */
@Component
public class TopicServiceImpl extends AbstractDataAccessService implements TopicService {

    @Override
    public Long addTopic(String title, Long author, Long category) {
        Topic newTopic = new Topic();
        newTopic.setTitle(title);
        newTopic.setAuthor(genericDao.loadById(author, User.class));
        newTopic.setCategory(genericDao.loadById(category, Category.class));
        
        return genericDao.saveOrUpdate(newTopic).getId();
    }

    @Override
    public void deleteTopic(Long userId) {
        genericDao.removeById(userId, Topic.class);
    }
    
    @Override
    public List<TopicDto> getAllTopics() {
        List<Topic> topics = genericDao.getAll(Topic.class);
        List<TopicDto> topicDtos = new ArrayList<TopicDto>();

        for (Topic t : topics) {
            topicDtos.add(new TopicDto(t.getId(), t.getTitle(), HibernateTools.getIdentifier(t.getAuthor()), HibernateTools.getIdentifier(t.getCategory()) ,DtoTransformerHelper.getIdentifiers(t.getPosts()), t.getCreated(), t.getUpdated()));
        }
        return topicDtos;
    }

    @Override
    public List<TopicDto> getTopicsByCategory(Long categoryId) {
        List<Topic> topics = genericDao.getByProperty("category", genericDao.loadById(categoryId, Category.class), Topic.class, "DESC");
        List<TopicDto> topicDtos = new ArrayList<TopicDto>();
        for (Topic t : topics) {
            topicDtos.add(new TopicDto(t.getId(), t.getTitle(), HibernateTools.getIdentifier(t.getAuthor()), HibernateTools.getUserDto(t.getAuthor()), HibernateTools.getIdentifier(t.getCategory()) ,DtoTransformerHelper.getIdentifiers(t.getPosts()), t.getCreated(), t.getUpdated()));
        }
        return topicDtos;
    }

    @Override
    public TopicDto getTopicById(Long id) {
        Topic t = genericDao.getByPropertyUnique("id", id, Topic.class);
        return new TopicDto(t.getId(), t.getTitle(), HibernateTools.getIdentifier(t.getAuthor()), HibernateTools.getIdentifier(t.getCategory()), DtoTransformerHelper.getIdentifiers(t.getPosts()), t.getCreated(), t.getUpdated());
    }
    
    @Override
    public PostDto getLastPostFromTopic(Long id) {
        Post p = genericDao.getByPropertyUnique("topic_id", id, Post.class);
        return new PostDto(p.getId(), p.getTitle(), p.getContent(), HibernateTools.getIdentifier(p.getAuthor()), HibernateTools.getIdentifier(p.getTopic()), p.getCreated(), p.getUpdated());
    }
    
}