package com.jinipl.retrotech.config;

import com.jinipl.retrotech.model.Order;
import com.jinipl.retrotech.model.ShoppingCart;
import com.jinipl.retrotech.repos.ProductRepository;
import com.jinipl.retrotech.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }
    @Bean
    public ShoppingCart shoppingCart(ProductService productService) {
        return new ShoppingCart(productService);
    }

    @Bean
    public Order registerOrder() {
        return new Order();
    }

}
