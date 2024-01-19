package InxhinieriSofti.projekt.Repositories;

import InxhinieriSofti.projekt.Models.Course;
import InxhinieriSofti.projekt.Models.Feedback;
import InxhinieriSofti.projekt.Models.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findByUserAndCourse(User user, Course course);

    List<Feedback> findAllByCourse(Course course);

    void deleteByFeedbackDateBefore(LocalDate date);

    List<Feedback> findByCourseIdOrderByFeedbackDateDesc(Long courseId);

    void deleteByFeedbackDateBefore(LocalDateTime date);
}
