package Auth.api;

import Auth.dao.UserDao;
import Auth.model.User;
import Auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllPeople();
    }
}
