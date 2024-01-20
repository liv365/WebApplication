package InxhinieriSofti.projekt.Services;

import InxhinieriSofti.projekt.DataObjects.UserDTO;
import InxhinieriSofti.projekt.Models.User;
import InxhinieriSofti.projekt.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(UserDTO userDTO) {
        //Kontroll nëse emaili dhe username ekzistojn apo jo

        if (userRepository.findByUsername(userDTO.getUsernameDTO()) != null ||
                userRepository.findByEmail(userDTO.getEmailDTO()) != null) {
            throw new RuntimeException("Username or email already exists");
        }

        User newUser = new User("John", "Joe", "john_joe", "john@example.com", "password123", "03-02-2015");
        newUser.setUsername(userDTO.getUsernameDTO());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPasswordDTO())); // Ensure to use a password encoder
        newUser.setEmail(userDTO.getEmailDTO());
//        newUser.setRegistrationDate(LocalDateTime.now());
        newUser.setRegistrationDate(LocalDateTime.now());

        return userRepository.save(newUser);
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>());
    }


    public void registerInitialUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User("John","Joe","john_joe","john@example.com", "password123","03-02-2015"));
        users.add(new User("Janet","Smith","janet_smith", "jane@example.com", "jane1234","12-01-2024"));


        userRepository.saveAll(users);
    }


}
