package InxhinieriSofti.projekt.Repositories;

import InxhinieriSofti.projekt.Models.Course;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select c from Course c where c.course_name =?1")
    Course findByCourse_name(String course_name);

    @Query("SELECT c FROM Course c ORDER BY c.averageRating DESC NULLS LAST")
    List<Course> findTopCoursesOrderByAverageRating(Pageable pageable);
}
