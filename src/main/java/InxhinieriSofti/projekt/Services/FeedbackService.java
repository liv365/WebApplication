package InxhinieriSofti.projekt.Services;

import InxhinieriSofti.projekt.Models.Feedback;
import InxhinieriSofti.projekt.Repositories.FeedbackRepository;
import InxhinieriSofti.projekt.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;


    public void leaveFeedback(Long courseId, String username, String description, int rating) {

        Feedback feedback = new Feedback();

        feedback.setUser(userRepository.findByUsername(username));
        feedback.setDescription(description);
        feedback.setRating((long) rating);
        feedback.setFeedbackDate(LocalDateTime.now());

        feedbackRepository.save(feedback);
    }

   public List<Feedback> getFeedbackByCourseId(Long courseId) {
        return feedbackRepository.findByCourseIdOrderByFeedbackDateDesc(courseId);
    }

    public double calculateAverageRating(Long courseId) {
        List<Feedback> feedbackList = getFeedbackForCourse(courseId);

        if (feedbackList.isEmpty()) {
            return 0.0;
        }
        double totalRating = 0.0;
        for (Feedback feedback : feedbackList) {
            totalRating += feedback.getRating();
        }
        return totalRating / feedbackList.size();
    }


    public List<Feedback> getFeedbackForCourse(Long courseId) {
        return feedbackRepository.findByCourseIdOrderByFeedbackDateDesc(courseId);
    }


    //për të fshirë komentet që kanë më shumë se 1 vit
    public void deleteOldFeedback() {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        feedbackRepository.deleteByFeedbackDateBefore(oneYearAgo);
    }


    //Që të ekzekutohet çdo natë në 00:00
    @Scheduled(cron="0 0 0 * * ?")
    public void scheduleRemoveOldFeedback(){
        deleteOldFeedback();
    }


}
