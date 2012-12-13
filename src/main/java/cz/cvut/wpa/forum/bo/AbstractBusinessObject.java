package cz.cvut.wpa.forum.bo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author vlcekmi3
 */
@MappedSuperclass
public class AbstractBusinessObject implements Serializable {
    @Id
    @GeneratedValue(generator="system-sequence")
    @GenericGenerator(name="system-sequence", strategy = "sequence")    
    protected Long id;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    
    /**
     * Updating timestamps before each transaction.
     */
    @PreUpdate
    @PrePersist
    public void updateTimeStamps() { 
        updated = new Date();
        if (created==null) {
            created = new Date();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractBusinessObject other = (AbstractBusinessObject) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
}