package InxhinieriSofti.projekt.Services;

import InxhinieriSofti.projekt.Models.Course;
import InxhinieriSofti.projekt.Models.Registration;
import InxhinieriSofti.projekt.Models.User;
import InxhinieriSofti.projekt.Repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;



    public List<Registration> getRegistratpionByUserId(Long userId){
        return registrationRepository.findByUserId(userId);
    }

    public int getRegisteredStudentsCount(Long courseId) {
        return registrationRepository.countByCourseId(courseId);
    }

    public List<Registration> getRegistrationByCourseId(Long courseId){
        return registrationRepository.findByCourseId(courseId);
    }


    public void registerUserForCourse(String username, Long courseId) {
        if (!isUserRegistered(username, courseId)) {
            Registration registration = new Registration();

            User user = new User("John", "Joe", "john_joe", "john@example.com", "password123", "03-02-2015");
            user.setUsername(username);
            registration.setUser(user);

            Course course = new Course();
            course.setId(courseId);
            registration.setCourse(course);
            registrationRepository.save(registration);
        }
    }



    public void dropUserFromCourse(String username, Long courseId) {
        Registration registration = registrationRepository.findByUserUsernameAndCourseId(username, courseId);
        if (registration != null) {
            registrationRepository.delete(registration);
        }
    }


    public boolean isUserRegistered(String username, Long courseId) {
        return registrationRepository.existsByUserUsernameAndCourseId(username, courseId);
    }


}
