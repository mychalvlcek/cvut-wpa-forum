package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.service.CategoryService;
import cz.cvut.wpa.forum.service.PostService;
import cz.cvut.wpa.forum.service.TopicService;
import cz.cvut.wpa.forum.service.UserService;
import cz.cvut.wpa.forum.dto.PostDto;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author vlcekmi3
 */
public class PostServiceImplTest extends AbstractServiceTest {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;

    public PostServiceImplTest() {
        super();
    }

    @Test
    public void testAddAndRetrievePost() {
        Long userId = addUser();
        Long categoryId = addCategory();
        Long topicId = addTopic(userId, categoryId);

        String title = "Nadpis";
        String content = "Obsah";

        Long postId = postService.addPost(title, content, userId, topicId);
        List<PostDto> posts = postService.getUsersPosts(userId);
        assertEquals(1, posts.size());

        PostDto post = posts.get(0);

        assertEquals(title, post.getTitle());
        assertEquals(userId, post.getAuthor());
        assertEquals(postId, post.getId());
    }

    @Test
    public void testAddAndRemovePost() {
        Long userId = addUser();
        Long categoryId = addCategory();
        Long topicId = addTopic(userId, categoryId);

        String title = "Nadpis";
        String content = "Obsah";

        Long postId = postService.addPost(title, content, userId, topicId);
        assertEquals(1, postService.getAllPosts().size());
        postService.deletePost(postId);
        assertEquals(0, postService.getAllPosts().size());
    }
    
    @Test
    public void testPostDeletedWhenUserRemoved() {
        Long userId = addUser();
        Long categoryId = addCategory();
        Long topicId = addTopic(userId, categoryId);

        String title = "Nadpis";
        String content = "Obsah";

        postService.addPost(title, content, userId, topicId);
        assertEquals(1, postService.getAllPosts().size());
        
        userService.deleteUser(userId);    
        assertEquals(0, postService.getAllPosts().size());
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
    
    private long addTopic(Long userId, Long categoryId) {
        String title = "Title" + System.currentTimeMillis();
        Long author = userId;
        Long category = categoryId;
        return topicService.addTopic(title, author, category);
    }
}
