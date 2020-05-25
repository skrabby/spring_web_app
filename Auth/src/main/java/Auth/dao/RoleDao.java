package Auth.dao;

import Auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("users_roles")
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
