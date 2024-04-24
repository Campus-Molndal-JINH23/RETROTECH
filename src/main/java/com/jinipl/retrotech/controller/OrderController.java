package com.jinipl.retrotech.controller;

import com.jinipl.retrotech.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final Order order;

    @Autowired
    public OrderController(Order order) {
        this.order = order;
    }

    @GetMapping()
    public String showOrder(Model model) {
        model.addAttribute("order", order);
        return "order_confirmation";
    }

}
