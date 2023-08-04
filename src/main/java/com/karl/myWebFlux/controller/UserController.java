package com.karl.myWebFlux.controller;

import com.karl.myWebFlux.domain.User;
import com.karl.myWebFlux.respository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    /**
     * @Description: 以数组形式一次返回
     * @param:
     * @return reactor.core.publisher.Flux<com.karl.myWebFlux.domain.User>
     * @author zhouzhi96
     * @date 2023/8/4 15:43
     */
    @GetMapping("/all")
    public Flux<User> getAll(){
        return userRepository.findAll();
    }

    /**
     * @Description: SSE形式多次返回数据
     * @param:
     * @return reactor.core.publisher.Flux<com.karl.myWebFlux.domain.User>
     * @author zhouzhi96
     * @date 2023/8/4 15:44
     */
    @GetMapping(value="/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll(){
        return userRepository.findAll();
    }

    @PostMapping("/add")
    public Mono<User> addUser(@RequestBody User user){
        // spring data jpa 里面，新增和修改都是save，有id是修改，无id是新增
        user.setId(null);
        return this.userRepository.save(user);
    }
    @PostMapping("/update")
    public Mono<User> updateUser(@RequestBody User user){
        // spring data jpa 里面，新增和修改都是save，有id是修改，无id是新增
//        user.setId(null);
        return this.userRepository.save(user);
    }
}
