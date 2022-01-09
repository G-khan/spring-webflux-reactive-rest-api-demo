package dev.gokhana.reactiveapi.service;

import dev.gokhana.reactiveapi.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

// Dummy Service for reactive api
@Service
public class UserServiceImpl implements UserService {

    @Override
    public Mono<User> getUserById(int id) {
        return Mono.just(new User(id, "Gokhan", new Random().nextInt(100) + 1));
    }

    @Override
    public Flux<User> getUsers() {
        return Flux.just(
                new User(1, "Hello", new Random().nextInt(100) + 1),
                new User(2, "from", new Random().nextInt(100) + 1),
                new User(3, "Spring Webflux", new Random().nextInt(100) + 1)
        );
    }

    @Override
    public Mono<User> saveUser(Mono<User> userMono) {
        return userMono.map(user -> {
            user.setId(new Random(1000).nextInt() + 1);
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


}
