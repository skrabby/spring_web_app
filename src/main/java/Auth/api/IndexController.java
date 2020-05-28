package Auth.api;

import Auth.model.User;
import Auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    private Map<Long, User> userMap = new HashMap<>();

    @GetMapping("/")
    public String mainPage() {
        return "master/landing-page";
    }

    @GetMapping("/login")
    public String loginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String role = auth.getAuthorities().toString();
            if (role.contains("ADMIN"))
                return "redirect:/admin/dashboard";
            else if (role.contains("TEACHER"))
                return "redirect:/teacher/dashboard";
            else
                return "redirect:/student/dashboard";
        }
        return "master/login-page";
    }

    @GetMapping("/login-success")
    public String loginSuccess() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
        if (role.contains("ADMIN"))
            return "redirect:/admin/dashboard";
        else if (role.contains("TEACHER"))
            return "redirect:/teacher/dashboard";
        else
            return "redirect:/student/dashboard";
    }
    /*
    @GetMapping("/registration")
    public String registrationPage() {
        return "master/registration-page";
    }

    @PostMapping("/registration")
    public String insertUser(User user, Map<String, Object> model) {
        userService.insertUser(user, model);
        return "redirect:/login";
    }
    */
    @GetMapping("/admin/dashboard")
    public String adminDashboardPage(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {
        if (result.hasErrors())
            return "error";
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        userMap.put(user.getId(), user);
        return "dashboard/dashboard";
    }

    @GetMapping("/teacher/dashboard")
    public String teacherDashboardPage() {
        return "dashboard/dashboard";
    }

    @GetMapping("/student/dashboard")
    public String studentDashboardPage() {
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
