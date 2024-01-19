package InxhinieriSofti.projekt.Models;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Table(name="course")
@Entity
public class Course {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="course_name")
    private String course_name;

    @Column(name="description")
    private String description;

    @Column(name="lecturer")
    private String lecturer;

    @Column(name="schedule")
    private String schedule;

    @Column(name="location")
    private String location;

    @Column(name= "max_capacity")
    private Long maxCapacity;

    @OneToMany(mappedBy = "course" , cascade = CascadeType.ALL)
    private List<Registration> registrationList;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<User> user;
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    private List<Feedback> feedback;

    public Course() {}

    public Course(final String course_name, final String description,final String lecturer,final String schedule, final String location, final Long maxCapacity) {
        this.course_name = course_name;
        this.description = description;
        this.lecturer = lecturer;
        this.schedule = schedule;
        this.location = location;
        this.maxCapacity = maxCapacity;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCourse_name() {
        return this.course_name;
    }

    public void setCourse_name(final String course_name) {
        this.course_name = course_name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getLecturer() {
        return this.lecturer;
    }

    public void setLecturer(final String lecturer) {
        this.lecturer = lecturer;
    }

    public String getSchedule() {
        return this.schedule;
    }

    public void setSchedule(final String schedule) {
        this.schedule = schedule;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public Long getMaxCapacity() {
        return this.maxCapacity;
    }

    public void setMaxCapacity(final Long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }




}
