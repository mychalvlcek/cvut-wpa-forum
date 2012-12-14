package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.bo.Post;
import cz.cvut.wpa.forum.bo.Topic;
import cz.cvut.wpa.forum.bo.User;
import cz.cvut.wpa.forum.dto.PostDto;
import cz.cvut.wpa.forum.helper.HibernateTools;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author vlcekmi3
 */
@Component
public class PostServiceImpl extends AbstractDataAccessService implements PostService {
    
    @Override
    public Long addPost(String title, String content, Long author, Long topic) {
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setContent(content);
        newPost.setAuthor(genericDao.loadById(author, User.class));
        newPost.setTopic(genericDao.loadById(topic, Topic.class));
        
        return genericDao.saveOrUpdate(newPost).getId();
    }


    @Override
    public void deletePost(Long postId) {
        genericDao.removeById(postId, Post.class);
    }
    
    @Override
    public void deletePostByTopic(Long topicId) {
        genericDao.removeByProperty("topic", genericDao.loadById(topicId, Topic.class),Post.class);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = genericDao.getAll(Post.class);
        List<PostDto> postDtos = new ArrayList<PostDto>();
        
        for(Post p : posts) {
            postDtos.add(new PostDto(p.getId(), p.getTitle(), p.getContent(), HibernateTools.getIdentifier(p.getAuthor()), HibernateTools.getIdentifier(p.getTopic()), p.getCreated(), p.getUpdated()));
        }
        return postDtos;        
    }
    
    @Override
    public PostDto getPostById(Long postId) {
        Post p = genericDao.getById(postId, Post.class);
        return new PostDto(p.getId(), p.getTitle(), p.getContent(), HibernateTools.getIdentifier(p.getAuthor()), HibernateTools.getUserDto(p.getAuthor()), HibernateTools.getIdentifier(p.getTopic()), p.getCreated(), p.getUpdated());
    }
    
    @Override
    public List<PostDto> getPostsByTopic(Long topicId) {
        List<Post> posts = genericDao.getByProperty("topic", genericDao.loadById(topicId, Topic.class), Post.class);
        List<PostDto> postDtos = new ArrayList<PostDto>();
        
        for(Post p : posts) {
            postDtos.add(new PostDto(p.getId(), p.getTitle(), p.getContent(), HibernateTools.getIdentifier(p.getAuthor()), HibernateTools.getUserDto(p.getAuthor()), HibernateTools.getIdentifier(p.getTopic()), p.getCreated(), p.getUpdated()));
        }
        return postDtos;        
    }
    
    @Override
    public List<PostDto> getUsersPosts(Long userId) {
        List<Post> posts = genericDao.getByProperty("author", genericDao.loadById(userId, User.class), Post.class, "DESC");
        List<PostDto> postDtos = new ArrayList<PostDto>();
        
        for(Post p : posts) {
            postDtos.add(new PostDto(p.getId(), p.getTitle(), p.getContent(), HibernateTools.getIdentifier(p.getAuthor()), HibernateTools.getUserDto(p.getAuthor()), HibernateTools.getIdentifier(p.getTopic()), p.getCreated(), p.getUpdated()));
        }
        return postDtos;        
    }

}