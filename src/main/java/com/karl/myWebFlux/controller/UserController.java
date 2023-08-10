package com.karl.myWebFlux.controller;

import com.karl.myWebFlux.domain.User;
import com.karl.myWebFlux.respository.UserRepository;
import com.karl.myWebFlux.utils.CheckUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月04日: 13:59
 */
@Slf4j
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
    public Flux<User> getAll(@RequestHeader Map<String, String> headers){
        log.info("getall");
        headers.forEach((key, value) -> {
            // 日志中输出所有请求头
            log.info(String.format("Header '%s' = %s", key, value));
        });
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
    public Mono<User> addUser(@Valid @RequestBody User user){
        // spring data jpa 里面，新增和修改都是save，有id是修改，无id是新增
        user.setId(null);
        CheckUtils.checkName(user.getName());
        return this.userRepository.save(user);
    }
    @PostMapping("/update")
    public Mono<User> updateUser(@RequestBody User user){
        // spring data jpa 里面，新增和修改都是save，有id是修改，无id是新增
//        user.setId(null);
        return this.userRepository.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") String id){
        return this.userRepository.findById(id)
                .flatMap(user -> this.userRepository.delete(user)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> updateUser(@PathVariable("id") String id, @Valid @RequestBody User user){
        return this.userRepository.findById(id)
                // 操作数据
                .flatMap(u -> {
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    return this.userRepository.save(u);
                })
                // 转换数据
                .map(u -> new ResponseEntity<User>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> findById(@PathVariable("id") String id){
        return this.userRepository.findById(id)
                .map(user -> new ResponseEntity<User>(user, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/age/{start}/{end}")
    public Flux<User> findByAgeBetween(@PathVariable("start") int start, @PathVariable("end") int end){
        return this.userRepository.findByAgeBetween(start, end);
    }

    @GetMapping(value = "/stream/age/{start}/{end}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindByAgeBetween(@PathVariable("start") int start, @PathVariable("end") int end){
        return this.userRepository.findByAgeBetween(start, end);
    }

    @GetMapping("/old")
    public Flux<User> oldUser(){
        return this.userRepository.oldUser();
    }

    @GetMapping(value = "/stream/old", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamOldUser(){
        return this.userRepository.oldUser();
    }
}
