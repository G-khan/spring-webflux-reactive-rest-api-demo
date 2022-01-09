package dev.gokhana.reactiveapi.service;

import dev.gokhana.reactiveapi.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserService {
    Mono<User> getUserById(int id);

    Flux<User> getUsers();

    Mono<User> saveUser(Mono<User> userDtoMono);

    Mono<User> updateUser(int id, Mono<User> userMono);

    Mono<Void> deleteUser(int id);
}
