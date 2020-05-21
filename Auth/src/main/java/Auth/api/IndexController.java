package Auth.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String mainPage() {
        return "index";
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
