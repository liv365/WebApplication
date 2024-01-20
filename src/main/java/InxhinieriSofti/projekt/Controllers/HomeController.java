package InxhinieriSofti.projekt.Controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@ControllerAdvice
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "Home";
    }

   @GetMapping("/user-profile")
    public String showUserProfilePage() {
        return "Profile";
    }
}
