package cz.cvut.wpa.forum.dto;

import java.util.List;

/**
 *
 * @author vlcekmi3
 */
public class CategoryDto extends AbstractDto {
    private String title;
    private List<Long> topics;
    
    public CategoryDto() {
        
    }

    public CategoryDto(Long id, String title, List<Long> topics) {
        this.id = id;
        this.title = title;
        this.topics = topics;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Long> getTopics() {
        return topics;
    }

    public void setTopics(List<Long> topics) {
        this.topics = topics;
    }

}
