package com.jinipl.retrotech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public Order registerOrder() {
        return new Order();
    }
}
