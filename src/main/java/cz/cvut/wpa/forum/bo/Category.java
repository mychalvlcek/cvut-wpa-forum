package cz.cvut.wpa.forum.bo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Represents category in the system.
 * @author vlcekmi3
 */
@Entity
public class Category extends AbstractBusinessObject {
    @Column(nullable = false)
    private String title;
    @OneToMany(mappedBy="category", cascade=CascadeType.REMOVE)
    private List<Topic> topics;

    
    /**
     * Add a topic to the list of topics in category
     * @param topic topic to be added
     */
    public void addTopic(Topic topic) {
        if(this.topics == null){
            topics = new ArrayList<Topic>();
        }
        if(!this.topics.contains(topic)){
            topics.add(topic);
        }
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
    
}
