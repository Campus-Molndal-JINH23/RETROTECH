package com.jinipl.retrotech.controller;

import com.jinipl.retrotech.model.Order;

import com.jinipl.retrotech.model.Product;
import com.jinipl.retrotech.model.ShoppingCart;
import com.jinipl.retrotech.repos.OrderRepository;
import com.jinipl.retrotech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    private final ShoppingCart shoppingCart;
    private final ProductService productService;
    public String discountValue;
    private Order order; // To store the current order being processed

    @Autowired
    public ShoppingCartController(ShoppingCart shoppingCart, Order order, ProductService productService) {
        this.shoppingCart = shoppingCart;
        this.order = order;

        this.productService = productService;
    }

    @GetMapping
    public String showShoppingCart(Model model, @ModelAttribute("discount") String discount) {
        model.addAttribute("cartItems", shoppingCart.getProducts());

        if (discount != null && !discount.isEmpty()) {
            model.addAttribute("discount", discount);
        } else {
            model.addAttribute("discount", "0"); // Set default discount to 0
        }
        discountValue = discount;
        // Calculate and add the total price
        model.addAttribute("totalPrice", shoppingCart.getTotalPrice(discount));

        return "shoppingcart";
    }


    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable String productId) {
        for (Product product : productService.showAllProducts()) {
            if (Objects.equals(product.getId(), productId)) {
                shoppingCart.addProduct(product);
                productService.removeProduct(product);
            }
        }
        return "redirect:/products";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable String productId) {
        shoppingCart.removeProductById(productId);// Lägger till en product tillbaka i ProductRepon i själva metoden i ShoppingCart.java
        return "redirect:/shoppingcart";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String name,
                           @RequestParam String address,
                           @RequestParam String postalCode,
                           @RequestParam String city,
                           @RequestParam String email) {
        order.setProducts(shoppingCart.getProducts());
        order.setTotalPrice(shoppingCart.getTotalPrice(discountValue));
        order.setName(name);
        order.setAddress(address);
        order.setPostalCode(postalCode);
        order.setCity(city);
        order.setEmail(email);
        //currentOrder.setDiscount(discount);
        //currentOrder.setOrderDate(LocalDateTime.now());

        shoppingCart.clearCart();

        return "redirect:/order";
    }

}
