package com.karl.myWebFlux.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月04日: 14:00
 */
@Document(collection = "user")
@Data
public class User {
    @Id
    private String id;

    private String name;

    private int age;
}
