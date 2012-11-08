package cz.cvut.wpa.forum.bo;

import javax.persistence.Entity;

/**
 * Entity, which represents roles of the system.
 * @author vlcekmi3
 */
@Entity
public class Role extends AbstractBusinessObject {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}