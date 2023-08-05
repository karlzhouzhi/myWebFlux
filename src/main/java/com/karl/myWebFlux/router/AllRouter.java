package com.karl.myWebFlux.router;

import com.karl.myWebFlux.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class AllRouter {

    @Bean
    RouterFunction<ServerResponse> userRouter(UserHandler userHandler){
        return RouterFunctions.nest(RequestPredicates.path("/user/router"),
                RouterFunctions.route(RequestPredicates.GET("/getAll"), userHandler::getAllUser)
                .andRoute(RequestPredicates.POST("/save").and(accept(MediaType.APPLICATION_JSON)), userHandler::saveUser)
                .andRoute(RequestPredicates.DELETE("/{id}"), userHandler::deleteById)
        );
    }
}
