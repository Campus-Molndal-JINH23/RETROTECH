package com.jinipl.retrotech;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Long> {
    Iterable<Product> findByCategory(String category);

    Iterable<Product> findByName(String name);
}
