package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.dto.PostDto;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
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
     * @param topic id of the topic
     * @return identifier of the newly added post
     */
    public Long addPost(String title, String content, Long author, Long topic);
    /**
     * Update post
     * @param id id of edited post
     * @param title title of the post
     * @param author id of the author
     * @param topic id of the topic
     * @return identifier of the newly added post
     */
    public Long updatePost(Long id, String title, String content, Long author, Long topic);
    /**
     * Deletes post from the system
     * @param postId idenfier of the post to be removed
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deletePost(Long postId);
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deletePostByTopic(Long topicId);
    /**
     * Get all posts stored in the system
     * @return 
     */
    @Transactional(readOnly=true)
    public List<PostDto> getAllPosts();
    
    public PostDto getPostById(Long postId);
    /**
     * Get all posts from given topic
     * @param topicId identifier of the topic
     * @return topic posts
     */
    public List<PostDto> getPostsByTopic(Long topicId);
    
    /**
     * Get all posts written by the given user
     * @param userId identifier of the user
     * @return users posts
     */
    @Transactional(readOnly=true)
    public List<PostDto> getUsersPosts(Long userId);
    
}