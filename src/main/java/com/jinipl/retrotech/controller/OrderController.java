package com.jinipl.retrotech.controller;

import com.jinipl.retrotech.model.Order;
import com.jinipl.retrotech.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping()
    public String showOrder(Model model) {
        // Fetch all orders from the repository
        List<Order> allOrders = orderRepository.findAll();

        // If there are no orders, set the model attribute to null
        Order latestOrder = !allOrders.isEmpty() ? allOrders.get(allOrders.size() - 1) : null;

        // Add the fetched order to the model
        model.addAttribute("order", latestOrder);

        // Return the view name
        return "order_confirmation";
    }
}
