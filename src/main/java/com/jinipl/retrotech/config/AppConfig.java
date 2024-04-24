package com.jinipl.retrotech.config;

import com.jinipl.retrotech.controller.ShoppingCartController;
import com.jinipl.retrotech.model.ShoppingCart;
import com.jinipl.retrotech.repos.OrderRepository;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class AppConfig {
    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

   /*@Bean
    public ShoppingCartController shoppingCartController(ShoppingCart shoppingCart, OrderRepository orderRepository) {
        return new ShoppingCartController(shoppingCart, orderRepository);
    }*/

    @Bean("shop")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }


   /* @Bean
    public MongoTemplate mongoTemplate() {
        ConnectionString connString = new ConnectionString(mongoUri);
        String databaseName = connString.getDatabase();
        if (databaseName == null || databaseName.isEmpty()) {
            throw new IllegalArgumentException("Database name is empty or null");
        }
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .build();
        return new MongoTemplate(MongoClients.create(settings), databaseName);
    }*/
}
