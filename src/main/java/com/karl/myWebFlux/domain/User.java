package com.karl.myWebFlux.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
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

    @NotBlank
    private String name;

    @Range(min=10, max=100)
    private int age;
}
