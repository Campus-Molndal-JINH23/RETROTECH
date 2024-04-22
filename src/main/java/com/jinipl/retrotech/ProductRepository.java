package com.jinipl.retrotech;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Long> {
    Iterable<Product> findByCategoryAndName(String category, String name);

}
