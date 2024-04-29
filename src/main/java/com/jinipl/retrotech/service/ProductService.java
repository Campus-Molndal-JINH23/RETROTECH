package com.jinipl.retrotech.service;

import com.jinipl.retrotech.model.Product;
import com.jinipl.retrotech.repos.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor

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

    public Optional<Product> findProductById(String id) {
        return productRepository.findById(id);
    }

    public void removeProduct(Product product) {productRepository.delete(product);}

    public void addProduct(Product product) {productRepository.save(product);}


}
