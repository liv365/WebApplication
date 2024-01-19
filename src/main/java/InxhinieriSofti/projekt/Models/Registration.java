package InxhinieriSofti.projekt.Models;

import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Table(name="registration_table")
@Entity
public class Registration {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="registration_date")
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    public Registration() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(final Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(final Course course) {
        this.course = course;
    }
}
