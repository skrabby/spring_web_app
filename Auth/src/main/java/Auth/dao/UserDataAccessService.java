package Auth.dao;

import Auth.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("postgres")
public class UserDataAccessService implements UserDao {
    @Override
    public int insertUser(UUID id, User user) {
        //insert db
        return 0;
    }

    @Override
    public List<User> selectAllUsers() {
        //access db
        return List.of(new User(UUID.randomUUID(), "POSTGRES DB"));
    }
}
