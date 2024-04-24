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

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    private final ShoppingCart shoppingCart;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private Order currentOrder; // To store the current order being processed

    @Autowired
    public ShoppingCartController(ShoppingCart shoppingCart, OrderRepository orderRepository, ProductService productService) {
        this.shoppingCart = shoppingCart;
        this.orderRepository = orderRepository;

        this.productService = productService;
    }

    @GetMapping
    public String showShoppingCart(Model model, String discount) {
        List<Product> cartItems = shoppingCart.getProducts();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("discount", discount);
        model.addAttribute("totalPrice", shoppingCart.getTotalPrice(discount));
        return "shoppingcart";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable String productId) {
        for (Product product : productService.showAllProducts()) {
            if (product.getId() == productId) {
                shoppingCart.addProduct(product);
                productService.removeProduct(product);
            }
        }
        return "redirect:/products";
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
        currentOrder.setTotalPrice(shoppingCart.getTotalPrice(discount));
        currentOrder.setName(name);
        currentOrder.setAddress(address);
        currentOrder.setPostalCode(postalCode);
        currentOrder.setCity(city);
        currentOrder.setEmail(email);
        currentOrder.setDiscount(discount);
        currentOrder.setOrderDate(LocalDateTime.now());

        orderRepository.save(currentOrder);

        shoppingCart.clearCart();

        return "redirect:/order_confirmation";
    }

}
