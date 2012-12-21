package cz.cvut.wpa.forum.dao;

import cz.cvut.wpa.forum.bo.AbstractBusinessObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

/**
 * Implementuje (hibernatem) akce GenericDao zpusobem obvyklym pro
 * prumerne dao.
 * 
 * Tato implementace je postavena nad JPA + rozsirenimi frameworku Hibernate
 * 
 * @author vlcekmi3
 */
@Component("genericDao")
public class GenericHibernateJpaDao implements GenericDao {

    @Autowired
    protected EntityManagerFactory entityManagerfactory;

    /**
     * Get entity manager for the current transaction
     * @return
     */
    protected EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerfactory); //entity manager with @Transactional support
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> clazz) {
        return getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e").getResultList();
    }
 
    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getAllOrderedDesc(String property, Class<ENTITY> clazz) {
        return getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e ORDER BY e.updated DESC").getResultList();
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getAllOrderedAsc(String property, Class<ENTITY> clazz) {
        throw new IllegalStateException("Not implemented yet");
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getByProperty(String property, Object value, Class<ENTITY> clazz) {
        String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e." + property + " = :value";
        return getEntityManager().createQuery(queryString).setParameter("value", value).getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getByProperty(String property, Object value, Class<ENTITY> clazz, String order) {
        String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e." + property + " = :value ORDER BY e.id " + order;
        return getEntityManager().createQuery(queryString).setParameter("value", value).getResultList();
    }

    public <ENTITY extends AbstractBusinessObject> void removeByProperty(String property, Object value, Class<ENTITY> clazz) {
        String queryString = "DELETE FROM " + clazz.getSimpleName() + " e WHERE e." + property + " = :value";
        getEntityManager().createQuery(queryString).setParameter("value", value);
    }

    @Override
    public <ENTITY extends AbstractBusinessObject> void removeById(long id, Class<ENTITY> clazz) {
        ENTITY e = getEntityManager().find(clazz, id);
        if (e != null) {
            getEntityManager().remove(e);
        }
    }

    /**
     * smaze danou entitu
     *
     * @param o entita ke smazani
     */
    public <ENTITY extends AbstractBusinessObject> void remove(ENTITY o) {
        getEntityManager().remove(o);
    }

    /**
     * Vrati objekt (pomoci get) dane tridy dle ID
     *
     * @param id id objektu k vraceni
     * @return objekt identifikovany id, @null pokud neexistuje
     */
    @SuppressWarnings("unchecked")
    public <ENTITY> ENTITY getById(Long id, Class<ENTITY> clazz) {
        return getEntityManager().find(clazz, id);
    }
    
    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getByIds(String property, List<Object> value, Class<ENTITY> clazz) {
        String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e." + property + " IN (:value)";
        return getEntityManager().createQuery(queryString).setParameter("value", value).getResultList();
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> ENTITY loadById(long id, Class<ENTITY> clazz) {
        return (ENTITY) ((Session) getEntityManager().getDelegate()).load(clazz, id);
    }

    public <ENTITY extends AbstractBusinessObject> ENTITY saveOrUpdate(ENTITY o) {
        if (o.getId() == null) {
            getEntityManager().persist(o);
        } else {
            getEntityManager().merge(o);
        }
        return o;
    }

    public <ENTITY> ENTITY getByPropertyUnique(String property, Object value, Class<ENTITY> clazz) {
        ENTITY e;
        if (value == null) {
            e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " IS NULL" ).getSingleResult());
        } else {
            e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " = :value" ).setParameter("value", value).getSingleResult());
        }
        return e;
    }

    public Long getCount(Class clazz) {
        throw new IllegalStateException("Not implemented yet");
    }

    public <ENTITY> List<ENTITY> getPage(int from, int maxResults, Class<ENTITY> clazz) {
        throw new IllegalStateException("Not implemented yet");
    }

    public <ENTITY> List<ENTITY> getPage(int first, int rows, String sortBy, boolean ascending, Class<ENTITY> clazz) {
        throw new IllegalStateException("Not implemented yet");
    }

    public void merge(Object o) {
        throw new IllegalStateException("Not implemented yet");
    }
}
