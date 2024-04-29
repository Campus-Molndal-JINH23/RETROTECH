package com.jinipl.retrotech.controller;

import com.jinipl.retrotech.model.Product;
import com.jinipl.retrotech.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {
    private ProductController productController;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = mock(ProductService.class);
        productController = new ProductController(productService);
    }

    @Test
    void showAllProducts() {
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product("1",
                "Kamera",
                "En bra kamera",
                1200.0,
                "Kamera",
                "bilder/kamera.jpg"));

        when(productService.showAllProducts()).thenReturn(expectedProducts);

        Model model = mock(Model.class);
        String viewName = productController.showAllProducts(model);

        verify(productService).showAllProducts();
        verify(model).addAttribute("products", expectedProducts);
        assertEquals("products", viewName);

    }

    @Test
    void showProduct() {
        String existingProductId = "1";
        Product expectedProduct = new Product(existingProductId, "Kamera",
                "En bra kamera",
                1200.0,
                "Kamera",
                "bilder/kamera.jpg");

        when(productService.findProductById(existingProductId)).thenReturn(java.util.Optional.of(expectedProduct));

        Model model = mock(Model.class);
        String viewName = productController.showProduct(existingProductId, model);

        verify(productService).findProductById(existingProductId);
        verify(model).addAttribute("product", expectedProduct);
        assertEquals("product_page", viewName);
    }

    @Test
    void searchProducts() {
        String searchCategory = "Spelkonsol";
        List<Product> expectedSearchResults = new ArrayList<>();
        expectedSearchResults.add(new Product("1", "Atari-spelkonsol",
                "Bra spelkonsol",
                1500,
                "Spelkonsol",
                "bilder/atari.jpg"));
        expectedSearchResults.add(new Product("2",
                "Sega-spelkonsol",
                "Bra spelkonsol",
                1500,
                "Spelkonsol",
                "bilder/sega.jpg"));

        when(productService.findProductsByCategory(searchCategory)).thenReturn(expectedSearchResults);

        Model model = mock(Model.class);
        String viewName = productController.searchProducts(searchCategory, model);

        verify(productService).findProductsByCategory(searchCategory);
        verify(model).addAttribute("searchResults", expectedSearchResults);
        assertEquals("search_results", viewName);
    }
}