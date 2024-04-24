package com.jinipl.retrotech.config;

import com.jinipl.retrotech.model.Order;
import com.jinipl.retrotech.model.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }

    @Bean
    public Order registerOrder() {
        return new Order();
    }

}
