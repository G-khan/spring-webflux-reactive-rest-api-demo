package dev.gokhana.reactiveapi.service;

import dev.gokhana.reactiveapi.client.UserWebClient;
import dev.gokhana.reactiveapi.model.User;
import dev.gokhana.reactiveapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

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
        int random = ThreadLocalRandom.current().nextInt(1, 100);
        return Mono.just(new User(id, "Gokhan", random));
    }

    @Override
    public Flux<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> saveUser(Mono<User> userMono) {
        return userMono.map(user -> {
            user.setId(ThreadLocalRandom.current().nextInt(1, 100));
            return user;
        });
    }

    @Override
    public Mono<User> updateUser(int id, Mono<User> userMono) {
        return userMono.map(user -> {
            user.setId(id);
            return user;
        });
    }

    @Override
    public Mono<Void> deleteUser(int id) {
        return Mono.empty();
    }


    @Override
    public Mono<User> getGuestUserById(int id) {
        return userWebClient.retrieveGuestUser(id);
    }
}
