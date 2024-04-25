package com.jinipl.retrotech.service;

import com.jinipl.retrotech.model.Product;
import com.jinipl.retrotech.repos.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor

public class ProductService {

    private ProductRepository productRepository;

    public List<Product> showAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public void removeProduct(Product product) {productRepository.delete(product);}

    public void addProduct(Product product) {productRepository.save(product);}


}
