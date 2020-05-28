package Auth.service;

import Auth.dao.RoleDao;
import Auth.dao.UserDao;
import Auth.model.Role;
import Auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("user_service")
public class UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    public UserService(@Qualifier("users") UserDao userDao, @Qualifier("users_roles") RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    /*
    public String insertUser(User user, Map<String, Object> model) {
        User userFromDb = userDao.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "User already exists.");
            return "registration";
        }
        Role role = roleDao.findByName("USER");
        user.setActive(true);
        user.setRoles(Arrays.asList(role));
        userDao.save(user);
        return "redirect:/login";
    }
    */

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken)
            return null;
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return userDao.findByUsername(userDetails.getUsername());
    }

    public User findByUsername(String name) {
        return userDao.findByUsername(name);
    }
}
