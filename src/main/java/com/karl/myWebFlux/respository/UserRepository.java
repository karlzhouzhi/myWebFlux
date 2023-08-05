package com.karl.myWebFlux.respository;

import com.karl.myWebFlux.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月04日: 14:02
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Flux<User> findByAgeBetween(int start, int end);

    @Query("{'age':{'$gte':20, '$lte':30}}")
    Flux<User> oldUser();
}
