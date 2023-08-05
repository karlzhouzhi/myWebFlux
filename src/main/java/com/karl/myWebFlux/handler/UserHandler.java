package com.karl.myWebFlux.handler;

import com.karl.myWebFlux.domain.User;
import com.karl.myWebFlux.respository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> getAllUser(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(this.userRepository.findAll(), User.class);
    }

    public Mono<ServerResponse> saveUser(ServerRequest request){
        Mono<User> user = request.bodyToMono(User.class);

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(this.userRepository.saveAll(user), User.class);
    }

    public Mono<ServerResponse> deleteById(ServerRequest request){
        String id = request.pathVariable("id");

        return this.userRepository.findById(id)
                .flatMap(user -> this.userRepository.delete(user).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
