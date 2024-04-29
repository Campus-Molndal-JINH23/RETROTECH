package com.jinipl.retrotech.model;

import com.jinipl.retrotech.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart cart;

    @Mock
    private ProductService productService;

    private Product product1;
    private Product product2;

    //Setup with 2 fake products and a cart to utilize.
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cart = new ShoppingCart(productService);
        product1 = new Product("1", "Digital Camera", "Kamera", "High-quality digital camera with advanced features", 200.00, 1, "images/camera.jpg");
        product2 = new Product("2", "Video Game Console", "Spel", "Latest model video game console with 4K resolution support", 300.00, 1, "images/console.jpg");
    }

    @Test
    void addProduct() {
        cart.addProduct(product1);
        assertTrue(cart.getProducts().contains(product1), "Product should be added to the cart");
    }

    @Test
    void removeProduct() {
        cart.addProduct(product1);
        cart.removeProduct(product1);
        assertFalse(cart.getProducts().contains(product1), "Product should be removed from the cart");
        verify(productService, times(1)).addProduct(product1);
    }

    @Test
    void removeProductById() {
        cart.addProduct(product1);
        cart.removeProductById(product1.getId());
        assertFalse(cart.getProducts().contains(product1), "Product should be removed from the cart by ID");
        verify(productService, times(1)).addProduct(product1);
    }

    @Test
    void clearCart() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        cart.clearCart();
        assertTrue(cart.getProducts().isEmpty(), "Cart should be empty after clearing");
    }
    //Checks both the total and that the discount is applied correctly.
    @Test
    void getTotalPrice() {
        cart.addProduct(product1);
        cart.addProduct(product2);
        double expectedTotal = product1.getPrice() + product2.getPrice();
        assertEquals((expectedTotal + 100), cart.getTotalPrice("0"), "Total price should equal the sum of product prices plus delivery less discount");
        assertEquals(((expectedTotal * 0.9) + 100), cart.getTotalPrice("10"), "Total price should equal the sum of product with 10% discount");

    }
}
