package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.dto.PostDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vlcekmi3
 */
@Transactional
public interface PostService {
    
    /**
     * Add post to the system
     * @param title title of the post
     * @param author id of the author
     * @return identifier of the newly added post
     */
    public Long addPost(String title, String content, Long author, Long topic);
    /**
     * Deletes post from the system
     * @param postId idenfier of the post to be removed
     */
    public void deletePost(Long postId);
    /**
     * Get all posts stored in the system
     * @return 
     */
    @Transactional(readOnly=true)
    public List<PostDto> getAllPosts();
    /**
     * Get all posts written by the given user
     * @param userId identifier of the user
     * @return users posts
     */
    @Transactional(readOnly=true)
    public List<PostDto> getUsersPosts(Long userId);
    
}