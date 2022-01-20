package dev.gokhana.reactiveapi.service;

import dev.gokhana.reactiveapi.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> getUserById(int id);

    Flux<User> getUsers();

    Mono<User> saveUser(User userDtoMono);

    Mono<User> updateUser(int id, User userMono);

    Mono<Void> deleteUser(int id);

    Mono<User>  getGuestUserById(int id);
}
