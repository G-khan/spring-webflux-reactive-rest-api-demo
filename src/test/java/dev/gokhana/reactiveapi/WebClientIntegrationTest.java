package dev.gokhana.reactiveapi;

import dev.gokhana.reactiveapi.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest(classes = ReactiveRestApiDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebClientIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void givenUser_whenCreateUser_thenReturnSuccessWithUser() {
        var score = ThreadLocalRandom.current().nextInt(1, 100);
        User request = new User(1,"gokhanadev", score);

        webTestClient.post().uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(request), User.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.name").isNotEmpty()
                .jsonPath("$.name").isEqualTo("gokhanadev")
                .jsonPath("$.score").isEqualTo(score);
    }

    @Test
    public void givenUserId_whenRetrieveUser_thenReturnSuccessWithUser() {
        webTestClient.get().uri("/api/v1/users/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.name").isNotEmpty();
    }


    @Test
    public void givenUserId_whenDeleteUser_thenReturnEmptyResponse() {
        webTestClient.delete().uri("/api/v1/users/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
    }

}
