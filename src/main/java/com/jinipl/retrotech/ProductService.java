package com.jinipl.retrotech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> showAllProducts() {
        return productRepository.findAll();
    }

    public Iterable<Product> findByCategoryAndName(String category, String name) {
        return productRepository.findByCategoryAndName(category, name);
    }

}
