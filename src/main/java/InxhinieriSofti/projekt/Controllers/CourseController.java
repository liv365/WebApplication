package InxhinieriSofti.projekt.Controllers;

import InxhinieriSofti.projekt.Models.Course;
import InxhinieriSofti.projekt.Models.Registration;
import InxhinieriSofti.projekt.Services.CourseService;
import InxhinieriSofti.projekt.Services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private RegistrationService registrationService;


    @GetMapping("/all")
    public String getAllCourses(Model model, Principal principal) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        model.addAttribute("username", principal.getName());
        return "CourseList";
    }

//    @GetMapping("/{courseId}")
//    public String getCourseDetails(@PathVariable Long courseId, Model model, Principal principal) {
//        Course course = courseService.getCourseById(courseId);
//        boolean isRegistered = registrationService.isUserRegistered(principal.getName(), courseId);
//        model.addAttribute("course", course);
//        model.addAttribute("isRegistered", isRegistered);
//        return "CourseDescription";
//    }

    @GetMapping("/{courseId}/details")
    public String getCourseDetails(@PathVariable Long courseId, Model model, Principal principal) {
        Course course = courseService.getCourseById(courseId);
        boolean isRegistered = registrationService.isUserRegistered(principal.getName(), courseId);
        int registeredStudentsCount = registrationService.getRegisteredStudentsCount(courseId);
        model.addAttribute("course", course);
        model.addAttribute("isRegistered", isRegistered);
        model.addAttribute("registeredStudentsCount", registeredStudentsCount);
        return "CourseDescription";
    }

    @PostMapping("/join")
    public String joinCourse(@RequestParam Long courseId, Principal principal) {
        registrationService.registerUserForCourse(principal.getName(), courseId);
        return "redirect:/courses/all";
    }

    @PostMapping("/drop")
    public String dropCourse(@RequestParam Long courseId, Principal principal) {
        registrationService.dropUserFromCourse(principal.getName(), courseId);
        return "redirect:/courses/all";
    }

    @GetMapping("/course-calendar")
    public String viewCourseCalendar(Model model) {
        List<Course> allCourses = courseService.getAllCourses();
        model.addAttribute("courses", allCourses);
        return "CourseCalendar";
    }




//        @GetMapping("/top-rated-courses")
//        public String viewTopRatedCourses(Model model) {
//            List<Course> topRatedCourses = courseService.getTopRatedCourses(8);
//
//            model.addAttribute("topRatedCourses", topRatedCourses);
//
//            return "TopRatedCourses";
//        }
//
//        //other methods for handling course-related requests
//    }
//
//
//    @GetMapping("/course-calendar")
//    public String viewCourseCalendar(Model model) {
//        List<Course> allCourses = courseService.getAllCourses();
//
//        model.addAttribute("allCourses", allCourses);
//
//        return "CourseCalendar";
//    }
}
