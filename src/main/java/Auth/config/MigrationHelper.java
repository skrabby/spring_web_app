package Auth.config;

import Auth.dao.RoleDao;
import Auth.dao.UserDao;
import Auth.model.Role;
import Auth.model.User;
import Auth.service.RoleService;
import Auth.service.UserService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MigrationHelper {
    private static UserService userService;
    private static RoleService roleService;
    private static PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public static void addData(Session session) {
        createRole(session, "ADMIN");
        createRole(session, "TEACHER");
        createRole(session, "USER");
        createUser(session, "admin1", "admin");
        createUser(session, "teacher1", "teacher");
        createUser(session, "user1", "user");
        createUser(session, "user2", "user");
        HibernateUtil.shutdown();
    }

    private static void createUser(Session session, String u, String p) {
        Transaction transaction = null;
        try {
            User user = userService.findByUsername(u);
            if (user == null) {
                transaction = session.beginTransaction();
                user = new User();
                user.setActive(true);
                user.setUsername(u);
                user.setPassword(passwordEncoder.encode(p));
                user.setRoles(Arrays.asList(roleService.findByName(u.toUpperCase().substring(0, u.length() - 1))));
                user.setLastName("Иванов");
                user.setFirstName("Иван");
                user.setMiddleName("Иванович");
                user.setPhotoUrl("https://i.imgur.com/DZutEiW.jpg");
                session.saveOrUpdate(user);
                transaction.commit();
            }
        }
        catch (HibernateException ex) {
            if(transaction != null)
                transaction.rollback();
        }
    }

    private static void createRole(Session session, String name) {
        Transaction transaction = null;
        try
        {
            Role role = roleService.findByName(name);
            if (role == null) {
                transaction = session.beginTransaction();
                role = new Role();
                role.setName(name);
                session.saveOrUpdate(role);
                transaction.commit();
            }
        }
        catch (HibernateException ex)
        {
            if(transaction != null)
                transaction.rollback();
        }
    }
}
