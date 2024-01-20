package InxhinieriSofti.projekt.Services;

import InxhinieriSofti.projekt.Models.Course;
import InxhinieriSofti.projekt.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private FeedbackService feedbackService;

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }


    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));
    }

    public List<Course> getTopRatedCourses(int numberOfCourses) {
       //tërheqim gjithë kurset nga databaza , specifikisht nga tabela e kurseve
        List<Course> allCourses = courseRepository.findAll();

        allCourses.sort(Comparator.comparingDouble(this::calculateAverageRating).reversed());

        return allCourses.stream().limit(numberOfCourses).collect(Collectors.toList());
    }

private double calculateAverageRating(Course course){
    return feedbackService.calculateAverageRating(course.getId());
}

//    public List<Course> getTopRatedCourses(int limit) {
//
//        return courseRepository.findTopCoursesOrderByAverageRating(limit);
//    }



    //rregjistrime manuale të disa kurseve
    public void registerInitalCourses(){
        List<Course>  courses = new ArrayList<>();

        courses.add(new Course("Introduction to computer science.", "As an introductory course, it aims to foster a solid foundation for students \n" +
                "pursuing further studies in computer science and related fields. By the end of\n" +
                "the course, students should have acquired a basic proficiency in programming,\n" +
                "a conceptual understanding of computing principles, and the ability to approach\n" +
                "problem-solving through a computational lens. Overall, \"Introduction to Computer Science\" \n" +
                "lays the groundwork for a lifelong journey in the dynamic and ever-evolving realm of \n" +
                "computer science.", "Taylor Anderson","Every Monday 14:00-17:00" ,"Second Floor/Hall number 205/",60L));



        courses.add(new Course("Computer Architecture", "The course delves into instruction set architectures (ISAs) and their impact on \n" +
                "the design of processors. Students explore different types of ISAs and \n" +
                "understand how they influence the performance and functionality of a computer \n" +
                "system.", "Perez Lee","Every Wednesday 09:00-11:00" ,"Third Floor/Hall 303",100L));




        courses.add(new Course("Data structures", "The course extends its focus to advanced data structures such as trees and graphs,\n" +
                "emphasizing their applications in solving complex problems. Students also examine\n" +
                "the efficiency of algorithms associated with various data structures, introducing \n" +
                "the concept of time and space complexity analysis.", "Martin Lee","Every Friday 15:00-18:00" ,"Second Floor/Hall number 205/",65L));




        courses.add(new Course("Algorithms", "\"Algorithms\" is a pivotal course in computer science that delves into the design,\n" +
                "analysis, and implementation of efficient algorithms to solve computational \n" +
                "problems. The curriculum begins by introducing students to algorithmic thinking, \n" +
                "emphasizing the development of step-by-step procedures for solving a diverse range \n" +
                "of problems.", "Clark Sanchez","Every Monday 10:00-13:00" ,"Second Floor/Hall 205",80L));



        courses.add(new Course("Database Management Systems", "The curriculum covers the Structured Query Language (SQL), which is widely used \n" +
                "for interacting with relational databases.", "Allen King","Every Friday 09:00-11:00" ,"First Floor/101 Hall",78L));

    }



}
