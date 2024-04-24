package com.jinipl.retrotech.config;

import com.jinipl.retrotech.model.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean("order")
    public Order registerOrder() {
        return new Order();
    }
}
