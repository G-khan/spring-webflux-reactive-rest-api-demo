package dev.gokhana.reactiveapi.controller.functional;

import dev.gokhana.reactiveapi.model.User;
import dev.gokhana.reactiveapi.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    private final UserService userService;
    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> getUsers(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        userService.getUsers(), User.class
                );
    }
}
