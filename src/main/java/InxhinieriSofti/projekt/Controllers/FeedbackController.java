package InxhinieriSofti.projekt.Controllers;

import InxhinieriSofti.projekt.Models.Course;
import InxhinieriSofti.projekt.Models.Feedback;
import InxhinieriSofti.projekt.Services.FeedbackService;
import InxhinieriSofti.projekt.Services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;


    @Autowired
    private RegistrationService registrationService;


    @GetMapping("/leave-feedback/{courseId}")
    public String getLeaveFeedback(@PathVariable Long courseId, Model model, Principal principal) {
        boolean isRegistered = registrationService.isUserRegistered(principal.getName(), courseId);


        if (isRegistered) {
            model.addAttribute("courseId", courseId);
            return "LeaveFeedback";
        } else {
            return "redirect:/error";
        }
    }
    @PostMapping("/leave-feedback")
    public String leaveFeedback(@RequestParam Long courseId,
                                @RequestParam String description,
                                @RequestParam int rating,
                                Principal principal) {
        // Check if the student is registered for the course
        boolean isRegistered = registrationService.isUserRegistered(principal.getName(), courseId);

        if (isRegistered) {
            feedbackService.leaveFeedback(courseId, principal.getName(), description, rating);
            return "redirect:/courses/" + courseId;
        } else {
            // Redirect to an error page or handle accordingly
            return "redirect:/error";
        }
    }


    @GetMapping("/view-feedback/{courseId}")
    public String viewFeedback(@PathVariable Long courseId, Model model) {
        List<Feedback> feedbackList = feedbackService.getFeedbackForCourse(courseId);
        double averageRating = feedbackService.calculateAverageRating(courseId);

        model.addAttribute("feedbackList", feedbackList);
        model.addAttribute("averageRating", averageRating);

        return "FeedBackView";
    }
}
