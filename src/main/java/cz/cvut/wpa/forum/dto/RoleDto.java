package cz.cvut.wpa.forum.dto;

import java.util.List;

/**
 *
 * @author vlcekmi3
 */
public class RoleDto extends AbstractDto {
    private String name;
    private List<Long> users;

    public RoleDto() {
    }

    public RoleDto(Long id, String name, List<Long> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }
    
}