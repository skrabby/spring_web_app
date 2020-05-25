package Auth.service;

import Auth.dao.RoleDao;
import Auth.dao.UserDao;
import Auth.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
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
}
