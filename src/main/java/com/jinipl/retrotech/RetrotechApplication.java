package com.jinipl.retrotech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableMongoRepositories
@ComponentScan(basePackages = "com.jinipl.retrotech")
public class RetrotechApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetrotechApplication.class, args);
	}

}
