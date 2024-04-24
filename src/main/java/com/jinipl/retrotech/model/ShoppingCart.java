package com.jinipl.retrotech.model;

import com.jinipl.retrotech.Product;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("shoppingcart")
public class ShoppingCart {
    private List<Product> products = new ArrayList<>();


    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void removeProductById(String productId) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId().equals(productId)) {
                products.remove(i);
                break;
            }
        }
    }

    public void clearCart() {
        products.clear();
    }


    public double getTotalPrice(String discountPercentage) {
        double totalPrice = 0;
        double discount = 0; // Default discount is 0

        if (discountPercentage != null) {
            discount = Double.parseDouble(discountPercentage); // Parse discountPercentage to double
        }

        for (Product product : products) {
            totalPrice += product.getPrice();
        }

        if (discount > 0) {
            double discountAmount = (totalPrice * discount) / 100; // Calculate discount amount
            totalPrice -= discountAmount; // Apply discount to the total price
        }

        return totalPrice;
    }


}
