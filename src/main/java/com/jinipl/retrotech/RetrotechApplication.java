package com.jinipl.retrotech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class RetrotechApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetrotechApplication.class, args);
	}

}

