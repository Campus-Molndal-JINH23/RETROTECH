package com.jinipl.retrotech.repos;

import com.jinipl.retrotech.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    // You can add custom query methods here if needed
}