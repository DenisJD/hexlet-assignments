package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> createUser(final User user) {
        return userRepository.save(user);
    }

    public Mono<User> findUser(final Long id) {
        return userRepository.findById(id);
    }

    public Mono<User> updateUser(final Long id, final User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public Mono deleteUser(final Long id) {
        return userRepository.deleteById(id);
    }
    // END
}
