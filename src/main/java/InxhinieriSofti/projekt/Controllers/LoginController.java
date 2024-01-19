package InxhinieriSofti.projekt.Controllers;

import InxhinieriSofti.projekt.Models.User;
import InxhinieriSofti.projekt.Repositories.UserRepository;
import InxhinieriSofti.projekt.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userServie;

    @GetMapping("/login")
        public String loginForm(){
            return "Login";
        }
    @PostMapping(value="checkIfUserExists")
    @ResponseBody
    public Integer checkIfUserExists(@RequestParam(value="username") String username){

        Integer userStatus= 0;
        Optional<User> userClientOptional = Optional.ofNullable(userRepository.findByUsername(username));
    if (userClientOptional.isPresent()){
        userStatus =0;
    }else {
        userStatus=1;
    }
    return userStatus;

    }
}
