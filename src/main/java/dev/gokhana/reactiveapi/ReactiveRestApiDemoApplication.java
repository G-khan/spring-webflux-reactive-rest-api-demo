package dev.gokhana.reactiveapi;

import dev.gokhana.reactiveapi.model.User;
import dev.gokhana.reactiveapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import reactor.core.publisher.Flux;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class ReactiveRestApiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveRestApiDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserRepository repository) {
        return (args) -> {
            // save a couple of users
            var users = Flux.just(
                    new User("Hello", ThreadLocalRandom.current().nextInt(1, 100)),
                    new User("from", ThreadLocalRandom.current().nextInt(1, 100)),
                    new User("Spring Webflux", ThreadLocalRandom.current().nextInt(1, 100))
            );
            repository.saveAll(users);
        };
    }
}
