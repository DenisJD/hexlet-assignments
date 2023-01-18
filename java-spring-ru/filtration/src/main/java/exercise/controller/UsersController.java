package exercise.controller;

import com.querydsl.core.types.Predicate;
import exercise.model.QUser;
import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    // BEGIN
    @GetMapping(path = "")
    public Iterable<User> getUsers(@RequestParam(value = "firstName", required = false) String firstName,
                                   @RequestParam(value = "lastName", required = false) String lastName) {
        if (firstName == null && lastName == null) {
            return userRepository.findAll();
        }
        if (lastName == null) {
            return userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName));
        }
        if (firstName == null) {
            return userRepository.findAll(QUser.user.lastName.containsIgnoreCase(lastName));
        }
        return userRepository.findAll(QUser.user.firstName.containsIgnoreCase(firstName)
                .and(QUser.user.lastName.containsIgnoreCase(lastName)));

    }

    @GetMapping(path = "/adv")
    public Iterable<User> advGetUsers(@QuerydslPredicate(root = User.class) Predicate predicate) {
        return userRepository.findAll(predicate);
    }
    // END
}

