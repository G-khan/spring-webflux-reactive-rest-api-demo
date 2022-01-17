package dev.gokhana.reactiveapi.repository;

import dev.gokhana.reactiveapi.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    public Flux<User> findByName(String name);
}
