package Auth.service;

import Auth.dao.RoleDao;
import Auth.dao.UserDao;
import Auth.model.Role;
import Auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("role_service")
public class RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleService(@Qualifier("users_roles") RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public boolean exists(String name) {
        Role role = roleDao.findByName(name);
        return role != null ? true : false;
    }

    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}
