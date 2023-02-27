package dev.gokhana.reactiveapi.client;

import dev.gokhana.reactiveapi.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserWebClient {

    private static final String USERS_URL_TEMPLATE = "/users/{id}";

    Logger logger = LoggerFactory.getLogger(UserWebClient.class);

    private final WebClient webClient;

    public UserWebClient(WebClient webClient) {
        this.webClient = webClient;
    }


    public Mono<User> retrieveGuestUser(int userId) {
        return webClient
                .get()
                .uri(USERS_URL_TEMPLATE, userId)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new RuntimeException("There is an error while retrieving the user: " + userId)))
                .bodyToMono(User.class)
                .doOnError(error -> logger.error("There is an error while sending request {}", error.getMessage()))
                .onErrorResume(error -> Mono.just(new User()))
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(3)))
                .map(user -> {
                            user.setScore(ThreadLocalRandom.current().nextInt(1, 100));
                            return user;
                        }
                );
    }
}
