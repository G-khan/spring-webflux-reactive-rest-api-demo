package dev.gokhana.reactiveapi;

import dev.gokhana.reactiveapi.model.Address;
import dev.gokhana.reactiveapi.model.User;
import dev.gokhana.reactiveapi.repository.UserRepository;
import dev.gokhana.reactiveapi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class ReactiveRestApiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveRestApiDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserService userService) {
        return (args) -> {
            // save a couple of users
            var users = Flux.just(
                    new User("Gökhan", ThreadLocalRandom.current().nextInt(1, 100)
                            , new Address("istanbul")),
                    new User("Betül", ThreadLocalRandom.current().nextInt(1, 100), new Address("istanbul")),
                    new User("Zühtü", ThreadLocalRandom.current().nextInt(1, 100), new Address("istanbul"))
            );
            users.flatMap(userService::saveUser)
                    .subscribe(user -> System.out.println("Saved user: " + user));
        };
    }


}
