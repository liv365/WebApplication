package InxhinieriSofti.projekt.Repositories;

import InxhinieriSofti.projekt.Models.Registration;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUserId(Long userId);

    List<Registration> findByCourseId(Long courseId);

    int countByCourseId(Long courseId);

    boolean existsByUserUsernameAndCourseId(String username, Long courseId);

    Registration findByUserUsernameAndCourseId(String username, Long courseId);
}
