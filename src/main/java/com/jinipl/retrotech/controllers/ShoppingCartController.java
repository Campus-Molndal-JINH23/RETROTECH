package com.jinipl.retrotech.controllers;

import com.jinipl.retrotech.models.Order;
import com.jinipl.retrotech.models.Product;
import com.jinipl.retrotech.models.ShoppingCart;
import com.jinipl.retrotech.repos.OrderRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    private final ShoppingCart shoppingCart;
    private final OrderRepository orderRepository;
    private Order currentOrder; // To store the current order being processed

    @Autowired
    public ShoppingCartController(ShoppingCart shoppingCart, OrderRepository orderRepository) {
        this.shoppingCart = shoppingCart;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String showShoppingCart(Model model) {
        List<Product> cartItems = shoppingCart.getProducts();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", shoppingCart.getTotalPrice());
        return "shoppingcart";
    }

    @PostMapping("/add")
    public String addToCart(@ModelAttribute Product product) {
        shoppingCart.addProduct(product);
        return "redirect:/shoppingcart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable String productId) {
        shoppingCart.removeProductById(productId);
        return "redirect:/shoppingcart";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String name,
                           @RequestParam String address,
                           @RequestParam String postalCode,
                           @RequestParam String city,
                           @RequestParam String email,
                           @RequestParam String discount) {
        currentOrder = new Order();
        currentOrder.setProducts(shoppingCart.getProducts());
        currentOrder.setTotalPrice(shoppingCart.getTotalPrice());
        currentOrder.setName(name);
        currentOrder.setAddress(address);
        currentOrder.setPostalCode(postalCode);
        currentOrder.setCity(city);
        currentOrder.setEmail(email);
        currentOrder.setDiscount(discount);
        currentOrder.setOrderDate(LocalDateTime.now());

        orderRepository.save(currentOrder);

        shoppingCart.clearCart();

        return "redirect:/orderconfirmation";
    }

}
