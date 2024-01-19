package InxhinieriSofti.projekt.Controllers;

import InxhinieriSofti.projekt.DataObjects.UserDTO;
import InxhinieriSofti.projekt.Models.User;
import InxhinieriSofti.projekt.Services.RegistrationService;
import InxhinieriSofti.projekt.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "Registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("userDTO") @Valid UserDTO userDTO,
                               BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "Registration";
        }

        try {
            userService.registerNewUser(userDTO);
            return "redirect:/login?success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "Registration";
        }
    }


    @PostMapping("/join")
    public String joinCourse(@RequestParam Long courseId, Principal principal) {
        String username = principal.getName();
        if (!registrationService.isUserRegistered(username, courseId)) {
            registrationService.registerUserForCourse(username, courseId);
        }
        return "redirect:/courses/all";
    }



    @PostMapping("/drop")
    public String dropCourse(@RequestParam Long courseId, Principal principal) {
        String username = principal.getName();
        if (registrationService.isUserRegistered(username, courseId)) {
            registrationService.dropUserFromCourse(username, courseId);
        }
        return "redirect:/courses/all";
    }

}



//@Controller
//public class RegistrationController {
//
//    @GetMapping("/registration")
//    public String registrationForm(Model model){
//        model.addAttribute("user",new User());
//        return "Registration";
//    }
//
////    @PostMapping("/registration")
////    public String registration(User user){
////        ModelAndView modelAndView = new ModelAndView();
////        modelAndView.setViewName("Registration");
////        return modelAndView;
////        userRepository.save(user)
////        return "redirect:/login";
////    }
//
//
//    @Autowired
//    private RegistrationService registrationService;
//
//    @GetMapping("/join/{courseId}")
//    public String joinCourse(@PathVariable Long courseId, Principal principal){
//        //handle course joining logic
//        return "redirect:/courses";
//    }
//
//
//
//    @GetMapping("/drop/{courseId}")
//    public String dropCourse(@PathVariable Long courseId, Principal principal){
//        //handle course dropping logic
//        return "redirect:/courses";
//    }
//
//
//    //other methods for handling registration-related requests
//
//
//}
