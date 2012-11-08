package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.service.UserService;
import cz.cvut.wpa.forum.dto.UserDto;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author vlcekmi3
 */
public class UserServiceImplTest extends AbstractServiceTest{
    @Autowired
    private UserService userService;
    
    @Test
    public void testAddAndRetrieveUser() {
        String userName = "UserName";
        String passwd = "passwd";
        String email = "email@email.com";
        
        Long id = userService.addUser(userName, passwd, email);
        UserDto userDto = userService.getUserById(id);
        
        assertEquals(userName, userDto.getUserName());
        assertEquals(email, userDto.getEmail());
    }

    @Test
    public void testAddAndRemoveUser() {
        String userName = "UserName";
        String passwd = "passwd";
        String email = "email@email.com";
        
        Long id = userService.addUser(userName, passwd, email);
        assertEquals(1, userService.getAllUsers().size());
        userService.deleteUser(id);
        assertEquals(0, userService.getAllUsers().size());
    }      

}
