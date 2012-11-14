package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.bo.Role;
import cz.cvut.wpa.forum.dto.RoleDto;
import cz.cvut.wpa.forum.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author vlcekmi3
 */
@Component
public class RoleServiceImpl extends AbstractDataAccessService implements RoleService {

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = genericDao.getAll(Role.class);
        List<RoleDto> roleDtos = new ArrayList<RoleDto>();

        for (Role r : roles) {
            roleDtos.add(new RoleDto(r.getId(), r.getName(),DtoTransformerHelper.getIdentifiers(r.getUsers())));
        }
        
        return roleDtos;
    }

    @Override
    public Long addRole(String name) {
        Role newRole = new Role();
        newRole.setName(name);
        
        return genericDao.saveOrUpdate(newRole).getId();
    }

    @Override
    public void deleteRole(Long roleId) {
        genericDao.removeById(roleId, Role.class);
    }

    @Override
    public RoleDto getRoleById(Long id) {
        Role r = genericDao.getByPropertyUnique("id", id, Role.class);
        return new RoleDto(r.getId(), r.getName(),DtoTransformerHelper.getIdentifiers(r.getUsers()));
    }
    
}