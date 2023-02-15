package exercise.controller;

import exercise.model.User;
import exercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    public Mono<User> createUser(@RequestBody final User user) {
        return userService.createUser(user);
    }

    @GetMapping(path = "/{id}")
    public Mono<User> getUser(@PathVariable final Long id) {
        return userService.findUser(id);
    }

    @PatchMapping(path = "/{id}")
    public Mono<User> updateUser(@RequestBody final User user, @PathVariable final Long id) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping(path = "/{id}")
    public Mono deleteUser(@PathVariable final Long id) {
        return userService.deleteUser(id);
    }
    // END
}
