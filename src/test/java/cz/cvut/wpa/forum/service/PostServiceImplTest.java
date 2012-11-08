package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.service.UserService;
import cz.cvut.wpa.forum.service.PostService;
import cz.cvut.wpa.forum.dto.PostDto;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vlcekmi3
 */
public class PostServiceImplTest extends AbstractServiceTest {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;

    public PostServiceImplTest() {
        super();
    }

    @Test
    public void testAddAndRetrievePost() {
        Long userId = addUser();

        String title = "Bob a Bobek, kralici z klobouku";

        Long postId = postService.addPost(title, userId);
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
        
        String title = "Bob a Bobek, kralici z klobouku";
        Long bookId = postService.addPost(title, userId);
        assertEquals(1, postService.getAllPosts().size());
        postService.deletePost(bookId);
        assertEquals(0, postService.getAllPosts().size());
    }
    
    @Test
    public void testBookDeletedWhenUserRemoved(){
        Long userId = addUser();
        
        String title = "Bob a Bobek, kralici z klobouku";
        postService.addPost(title, userId);
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
}
