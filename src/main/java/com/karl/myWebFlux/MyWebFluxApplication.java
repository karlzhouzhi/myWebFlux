package com.karl.myWebFlux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class MyWebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyWebFluxApplication.class, args);
	}

}
