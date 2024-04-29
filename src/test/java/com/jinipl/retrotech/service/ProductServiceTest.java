package com.jinipl.retrotech.service;

import com.jinipl.retrotech.model.Product;
import com.jinipl.retrotech.repos.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceTest {
     Product product = new Product("1",
             "Kamera",
             "En bra kamera",
             1200.0,
             "Kamera",
             "bilder/kamera.jpg");

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void showAllProducts() {
        List<Product> expectedProducts = Arrays.asList(product, product);

        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.showAllProducts();

        assertEquals(expectedProducts.size(), actualProducts.size());
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void findProductsByCategory() {
        List<Product> expectedProducts = Arrays.asList(product, product);

        when(productRepository.findByCategory("Kamera")).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.findProductsByCategory("Kamera");

        assertEquals(expectedProducts.size(), actualProducts.size());
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void findProductById() {
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        Optional<Product> actualProduct = productService.findProductById("1");

        assertTrue(actualProduct.isPresent());
        assertEquals(product, actualProduct.get());
    }

    @Test
    void removeProduct() {
        productService.removeProduct(product);

        verify(productRepository).delete(product);
    }

    @Test
    void addProduct() {
        when(productRepository.save(product)).thenReturn(product);

        productService.addProduct(product);

        verify(productRepository).save(product);
    }
}