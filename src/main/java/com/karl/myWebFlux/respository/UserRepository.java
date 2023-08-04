package com.karl.myWebFlux.respository;

import com.karl.myWebFlux.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月04日: 14:02
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
