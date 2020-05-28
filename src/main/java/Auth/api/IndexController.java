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
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

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
    public ModelAndView adminDashboardPage() {
        ModelAndView modelAndView = new ModelAndView("admin-dashboard/dashboard");;
        User user = userService.getUser();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/teacher/dashboard")
        public ModelAndView teacherDashboardPage() {
            ModelAndView modelAndView = new ModelAndView("teacher-dashboard/dashboard");;
            User user = userService.getUser();
            modelAndView.addObject("user", user);
            return modelAndView;
    }

    @GetMapping("/student/dashboard")
    public ModelAndView studentDashboardPage() {
        ModelAndView modelAndView = new ModelAndView("student-dashboard/dashboard");;
        User user = userService.getUser();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/icons")
    public String iconsPage() {
        return "admin-dashboard/icons";
    }
}
