package InxhinieriSofti.projekt.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FeedbackScheduledTask {

    @Autowired
    private FeedbackService feedbackService;

    @Scheduled(cron = "0 0 0 * * *") // Run daily at midnight
    public void deleteOldFeedback() {
        feedbackService.deleteOldFeedback();
    }

}
