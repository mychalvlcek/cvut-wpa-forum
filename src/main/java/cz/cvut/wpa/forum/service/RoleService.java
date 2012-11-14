package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.dto.RoleDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vlcekmi3
 */
@Transactional
public interface RoleService {

    /**
     * Add category to the system
     * @param title title
     * @return identifier of the category stored
     */
    public Long addRole(String title);

    /**
     * Permanently removes the role
     * @param roleId id of the category to be removed
     */
    public void deleteRole(Long roleId);
    /**
     * Return role with the given id
     * @param id idenfier of the role to be retrieved
     * @return role with the given id, null if the role does not exist
     */
    @Transactional(readOnly=true)
    public RoleDto getRoleById(Long id);

    /**
     * Get all roles stored in the system
     * @return 
     */
    @Transactional(readOnly=true)
    public List<RoleDto> getAllRoles();
    
}