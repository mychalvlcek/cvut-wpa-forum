package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.dto.RoleDto;
import cz.cvut.wpa.forum.service.RoleService;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author vlcekmi3
 */
public class RoleServiceImplTest extends AbstractServiceTest {
    @Autowired
    private RoleService roleService;

    public RoleServiceImplTest() {
        super();
    }

    @Test
    public void testAddAndRetrieveRole() {
        String name = "role1";
        Long id = roleService.addRole(name);
        List<RoleDto> roles = roleService.getAllRoles();
        assertEquals(1, roles.size());
        RoleDto role = roleService.getRoleById(id);

        assertEquals(name, role.getName());
    }

    @Test
    public void testAddAndRemoveCategory() {
        String name = "role1";
        Long roleId = roleService.addRole(name);
        List<RoleDto> roles = roleService.getAllRoles();
        
        assertEquals(1, roleService.getAllRoles().size());
        roleService.deleteRole(roleId);
        assertEquals(0, roleService.getAllRoles().size());
    }

}