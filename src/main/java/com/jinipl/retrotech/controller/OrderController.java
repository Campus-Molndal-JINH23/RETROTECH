package com.jinipl.retrotech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final Order order;

    // TODO Skapa cart bean om inte redan gjort.
    private final Cart cart;

    @Autowired
    public OrderController(Order order, Cart cart) {
        this.order = order;
        this.cart = cart;
    }

    @GetMapping()
    public String showOrder(Model model) {
        model.addAttribute("order", order);
        return "order_confirmation";
    }

    @PostMapping("/create")
    public String createOrder(
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("postCode") String postCode,
            @RequestParam("city") String city,
            @RequestParam("email") String email
    ) {
        order.setName(name);
        order.setAddress(address);
        order.setPostCode(postCode);
        order.setCity(city);
        order.setEmail(email);
        order.setOrderTime(LocalDateTime.now());
        order.setProducts(cart.getProducts());
        order.setTotalPrice(cart.getProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum());
        return "redirect:/order";
    }
}
