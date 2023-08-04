package com.karl.myWebFlux.controller;

import com.karl.myWebFlux.domain.User;
import com.karl.myWebFlux.respository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月04日: 13:59
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public Flux<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping(value="/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll(){
        return userRepository.findAll();
    }
}
