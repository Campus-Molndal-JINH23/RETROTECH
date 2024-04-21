package com.jinipl.retrotech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Iterable<Product> showAllProducts(){
        return productRepository.findAll();
    }

    public Iterable<Product> showProductsByCategory(String category){
        return productRepository.findByCategory(category);
    }
    public Iterable<Product> findProductByName(String name){
        return productRepository.findByName(name);
    }
}
