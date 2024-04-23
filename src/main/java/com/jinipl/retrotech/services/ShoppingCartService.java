package com.jinipl.retrotech.services;

import com.jinipl.retrotech.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {

    private final List<Product> shoppingCart = new ArrayList<>();

    // Method to add a product to the shopping cart
    public void addToCart(Product product) {
        shoppingCart.add(product);
    }

    // Method to remove a product from the shopping cart
    public void removeFromCart(Product product) {
        shoppingCart.remove(product);
    }

    // Method to clear the shopping cart
    public void clearCart() {
        shoppingCart.clear();
    }

    // Method to get all products in the shopping cart
    public List<Product> getCartItems() {
        return new ArrayList<>(shoppingCart);
    }

    // Method to calculate the total price of items in the shopping cart
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product product : shoppingCart) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
