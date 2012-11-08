package cz.cvut.wpa.forum.service;

import cz.cvut.wpa.forum.bo.User;
import cz.cvut.wpa.forum.dto.UserDto;
import cz.cvut.wpa.forum.helper.DtoTransformerHelper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author vlcekmi3
 */
@Component
public class UserServiceImpl extends AbstractDataAccessService implements UserService {

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = genericDao.getAll(User.class);
        List<UserDto> userDtos = new ArrayList<UserDto>();

        for (User u : users) {
            userDtos.add(new UserDto(u.getId(), u.getUserName(), u.getEmail(), DtoTransformerHelper.getIdentifiers(u.getMessages()), DtoTransformerHelper.getIdentifiers(u.getPosts()), DtoTransformerHelper.getIdentifiers(u.getTopics()), DtoTransformerHelper.getIdentifiers(u.getRoles())));
        }
        return userDtos;
    }

    @Override
    public Long addUser(String userName, String password, String email) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setUserName(userName);

        return genericDao.saveOrUpdate(newUser).getId();
    }

    @Override
    public void deleteUser(Long userId) {
        genericDao.removeById(userId, User.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        User u = genericDao.getByPropertyUnique("id", id, User.class);
        return new UserDto(u.getId(), u.getUserName(), u.getEmail(), DtoTransformerHelper.getIdentifiers(u.getMessages()), DtoTransformerHelper.getIdentifiers(u.getPosts()), DtoTransformerHelper.getIdentifiers(u.getTopics()), DtoTransformerHelper.getIdentifiers(u.getRoles()));
    }
}
