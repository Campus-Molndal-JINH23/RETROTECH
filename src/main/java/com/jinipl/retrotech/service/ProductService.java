package com.jinipl.retrotech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> showAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

}
