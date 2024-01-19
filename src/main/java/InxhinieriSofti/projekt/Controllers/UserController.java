package InxhinieriSofti.projekt.Controllers;

import InxhinieriSofti.projekt.Models.User;
import InxhinieriSofti.projekt.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(){
    return "resources/templates/Registration";
}

    @PostMapping("/register")
    public String registerUser(User user, Model model){
        if(userRepository.findByUsername(user.getUsername()) != null){
            model.addAttribute("error", "User already exists.");
            return "register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "templates/Login";
    }
}
