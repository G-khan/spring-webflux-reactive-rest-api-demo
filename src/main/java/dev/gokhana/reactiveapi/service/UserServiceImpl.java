package dev.gokhana.reactiveapi.service;

import dev.gokhana.reactiveapi.client.UserWebClient;
import dev.gokhana.reactiveapi.model.User;
import dev.gokhana.reactiveapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    final UserWebClient userWebClient;
    private final UserRepository userRepository;

    public UserServiceImpl(UserWebClient userWebClient, UserRepository userRepository) {
        this.userWebClient = userWebClient;
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Flux<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> saveUser(User userDTO) {
        return userRepository.save(userDTO);
    }

    @Override
    public Mono<User> updateUser(int id, User userDTO) {
        return userRepository.findById(id).flatMap(user -> {
            userDTO.setId(user.getId()); // if there is something else to update do it here.
            return userRepository.save(userDTO);
        });
    }

    @Override
    public Mono<Void> deleteUser(int id) {
        return userRepository.deleteById(id);
    }


    @Override
    public Mono<User> getGuestUserById(int id) {
        return userWebClient.retrieveGuestUser(id);
    }
}
