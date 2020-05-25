package Auth.config;

import Auth.model.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MigrationHelper {

    public static void addData(Session session) {
        insertRoles(session, "ADMIN", 1L);
        insertRoles(session, "TEACHER", 2L);
        insertRoles(session, "USER", 3L);
        HibernateUtil.shutdown();
    }

    private static void insertRoles(Session session, String name, Long id) {
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            Role role = new Role();
            role.setName(name);
            role.setId(id);
            session.saveOrUpdate(role);
            transaction.commit();
        }
        catch (HibernateException ex)
        {
            if(transaction != null)
                transaction.rollback();
        }
    }
}
