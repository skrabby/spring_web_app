package Auth.service;

import Auth.dao.RoleDao;
import Auth.dao.UserDao;
import Auth.model.Role;
import Auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Service
public class UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public UserService(@Qualifier("users") UserDao userDao, @Qualifier("users_roles") RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public String insertUser(User user, Map<String, Object> model) {
        User userFromDb = userDao.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "User already exists.");
            return "registration";
        }
        Role role = roleDao.findByName("USER");
        user.setActive(true);
        user.setRole(role);
        userDao.save(user);
        return "redirect:/login";
    }

}
