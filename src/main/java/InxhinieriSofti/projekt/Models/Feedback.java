package InxhinieriSofti.projekt.Models;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Table(name="feedback_table")
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="description",length = 1000)
    private String description;

    @Column(name="rating")
    private Long rating;

    @Column(name="feedback_date")
    private Date feedbackDate;

    public Feedback() {

    }


    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Long getRating() {
        return this.rating;
    }

    public void setRating(final Long rating) {
        this.rating = rating;
    }

    public Date getFeedbackDate() {
        return this.feedbackDate;
    }


    public void setFeedbackDate(LocalDateTime now) {
    }
}
