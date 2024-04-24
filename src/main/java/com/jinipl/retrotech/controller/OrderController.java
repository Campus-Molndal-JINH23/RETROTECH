package com.jinipl.retrotech.controller;

import com.jinipl.retrotech.model.Order;
import com.jinipl.retrotech.model.ShoppingCart;
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
    private final ShoppingCart cart;

    @Autowired
    public OrderController(Order order, ShoppingCart cart) {
        this.order = order;
        this.cart = cart;
    }

    @GetMapping()
    public String showOrder(Model model) {
        model.addAttribute("order", order);
        return "order_confirmation";
    }

}
