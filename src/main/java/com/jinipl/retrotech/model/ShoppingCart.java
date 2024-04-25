package com.jinipl.retrotech.model;

import com.jinipl.retrotech.service.ProductService;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document("shoppingcart")
public class ShoppingCart {
    private ProductService productService;
    @Getter
    @Setter
    private List<Product> products = new ArrayList<>();

    public ShoppingCart(ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(Product product) {
        products.add(product);
    }



    public void removeProduct(Product product) {

        products.remove(product);
        productService.addProduct(product);

    }

    public void removeProductById(String productId) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getId().equals(productId)) {
                productService.addProduct(product);
                products.remove(i);
                break;
            }
        }
    }

    public void clearCart() {
        products.clear();
    }


    public double getTotalPrice(String discount) {
        double totalPrice = 0.0;
        double discountValue = 0.0;

        // Check if discount value is provided and parse it to double
        if (discount != null && !discount.isEmpty()) {
            try {
                discountValue = Double.parseDouble(discount);
            } catch (NumberFormatException e) {
                // Handle invalid discount value, log the error or set a default value
                // For now, let's set it to 0
                discountValue = 0.0;
            }
        }

        // Convert discount percentage to a factor (e.g., 20% -> 0.20)
        double discountFactor = discountValue / 100.0;

        // Calculate total price after applying the discount
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        double leveransAvgift = 100;
        // Apply the discount
        totalPrice *= (1 - discountFactor);

        return totalPrice + leveransAvgift;//Leveransavgift
    }



}
