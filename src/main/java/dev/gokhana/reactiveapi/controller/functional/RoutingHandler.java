package dev.gokhana.reactiveapi.controller.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RoutingHandler {

    private static final String apiPrefix = "/api/v1/funtional/users";

    @Bean
    public RouterFunction<ServerResponse> functionalRoutes(UserHandler userHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET(apiPrefix), userHandler::getUsers);
    }
}
