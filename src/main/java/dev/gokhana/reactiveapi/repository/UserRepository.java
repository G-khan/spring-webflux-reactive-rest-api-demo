package dev.gokhana.reactiveapi.repository;

import dev.gokhana.reactiveapi.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    @Query("select id, name, score from reactive_user where name = $1")
    Flux<User> findByName(String name);
    Mono<User> findById(int id);
    Mono<Void> deleteById(int id);
}
