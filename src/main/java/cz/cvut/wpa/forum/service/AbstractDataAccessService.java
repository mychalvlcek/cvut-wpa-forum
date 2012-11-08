/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.dao.GenericDao;

/**
 *
 * @author mickapa1
 */
public abstract class AbstractDataAccessService {
    protected GenericDao genericDao;
    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public GenericDao getGenericDao() {
        return genericDao;
    }    
}
