package Auth.dao;

import Auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("users")
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
