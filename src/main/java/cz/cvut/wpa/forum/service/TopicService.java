package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.bo.User;
import cz.cvut.wpa.forum.dto.PostDto;
import cz.cvut.wpa.forum.dto.TopicDto;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vlcekmi3
 */
@Transactional
public interface TopicService {
    
    /**
     * Add topic to the system
     * @param title title
     * @param author id of the user
     * @param category id of the category
     * @return identifier of the topic stored
     */
    public Long addTopic(String title, Long author, Long category);
    
    /**
     * Permanently removes the topic
     * @param topicId id of the topic to be removed
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteTopic(Long topicId);
    
    /**
     * Get all topics stored in the system
     * @return 
     */
    @Transactional(readOnly=true)
    public List<TopicDto> getAllTopics();
    
    /**
     * Get all topics from given category
     * @param id id of the category
     */
    @Transactional(readOnly=true)
    public List<TopicDto> getTopicsByCategory(Long id);

    /**
     * Return topic with the given id
     * @param id idenfier of the topic to be retrieved
     * @return topic with the given id, null if the topic does not exist
     */
    @Transactional(readOnly=true)
    public TopicDto getTopicById(Long id);
    
    @Transactional(readOnly=true)
    public PostDto getLastPostFromTopic(Long id);
    
}