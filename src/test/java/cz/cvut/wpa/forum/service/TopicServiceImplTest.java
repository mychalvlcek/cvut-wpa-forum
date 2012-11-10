package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.dto.TopicDto;
import cz.cvut.wpa.forum.service.CategoryService;
import cz.cvut.wpa.forum.service.TopicService;
import cz.cvut.wpa.forum.service.UserService;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author vlcekmi3
 */
public class TopicServiceImplTest extends AbstractServiceTest {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;

    public TopicServiceImplTest() {
        super();
    }

    @Test
    public void testAddAndRetrieveTopic() {
        Long userId = addUser();
        Long categoryId = addCategory();
        String title = "topic";
        Long id = topicService.addTopic(title, userId, categoryId);
        List<TopicDto> topics = topicService.getAllTopics();
        assertEquals(1, topics.size());
        TopicDto topic = topicService.getTopicById(id);

        assertEquals(title, topic.getTitle());
    }

    @Test
    public void testAddAndRemoveTopic() {
        Long userId = addUser();
        Long categoryId = addCategory();
        String title = "topic";
        Long topicId = topicService.addTopic(title, userId, categoryId);
        List<TopicDto> categories = topicService.getAllTopics();
        
        assertEquals(1, topicService.getAllTopics().size());
        topicService.deleteTopic(topicId);
        assertEquals(0, topicService.getAllTopics().size());
    }
    
    private long addUser() {
        String userName = "UserName" + System.currentTimeMillis();
        String passwd = "passwd" + System.currentTimeMillis();
        String email = "email@email.com";

        return userService.addUser(userName, passwd, email);
    }
    
    private long addCategory() {
        String title = "Category title";
        return categoryService.addCategory(title);
    }

}