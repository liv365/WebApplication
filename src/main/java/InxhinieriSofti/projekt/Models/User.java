package InxhinieriSofti.projekt.Models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Registration> registrationList;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Feedback> feedbacks;


    public User(String john, String joe, String johnJoe, String mail, String password123, String s){

    }

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public List<Registration> getRegistrationList() {
        return this.registrationList;
    }

    public void setRegistrationList(final List<Registration> registrationList) {
        this.registrationList = registrationList;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public LocalDateTime getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(final LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }



    public User(final String firstName, final String lastName, final String username,final String email, final String password, final LocalDateTime registrationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
         }
}
