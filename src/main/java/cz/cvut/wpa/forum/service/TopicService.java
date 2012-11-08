package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.dto.TopicDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vlcekmi3
 */
@Transactional
public interface TopicService {

    /**
     * Add user to the system
     * @param userName username
     * @param password password as an open text (will be stored in hashed form)
     * @param email email of the user
     * @return identifier of the user stored
     */
    public Long addTopic(String title, Long author, Long category);

    /**
     * Permanently removes the topic
     * @param topicId id of the topic to be removed
     */
    public void deleteTopic(Long topicId);
    /**
     * Return topic with the given id
     * @param id idenfier of the topic to be retrieved
     * @return topic with the given id, null if the topic does not exist
     */
    @Transactional(readOnly=true)
    public TopicDto getTopicById(Long id);

    /**
     * Get all users stored in the system
     * @return 
     */
    @Transactional(readOnly=true)
    public List<TopicDto> getAllTopics();
}
