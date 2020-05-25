package Auth.api;

import Auth.model.User;
import Auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String mainPage() {
        return "master/landing-page";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "master/registration-page";
    }


    @PostMapping("/registration")
    public String insertUser(User user, Map<String, Object> model) {
        userService.insertUser(user, model);
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard/dashboard";
    }

    @GetMapping("/user")
    public String userPage() {
        return "dashboard/user";
    }

    @GetMapping("/tables")
    public String tablesPage() {
        return "dashboard/tables";
    }

    @GetMapping("/typography")
    public String typographyPage() {
        return "dashboard/typography";
    }

    @GetMapping("/icons")
    public String iconsPage() {
        return "dashboard/icons";
    }

    @GetMapping("/map")
    public String mapPage() {
        return "dashboard/map";
    }

    @GetMapping("/maps")
    public String mapsPage() {
        return "dashboard/maps";
    }

    @GetMapping("/notifications")
    public String notificationsPage() {
        return "dashboard/notifications";
    }
}
