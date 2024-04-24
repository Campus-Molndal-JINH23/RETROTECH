package com.jinipl.retrotech.service;

import com.jinipl.retrotech.model.Product;
import com.jinipl.retrotech.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

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

    public void removeProduct(Product product) {productRepository.delete(product);    }

    public void addProduct(Product product) {productRepository.save(product);}
}
