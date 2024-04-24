package com.jinipl.retrotech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DiscountController {

    // Static variable to store the discount value
    private static String discountValue;

    @GetMapping("/discount")
    public String showDiscount(Model model) {
        // Pass the discount value to the Thymeleaf template
        model.addAttribute("discountValue", discountValue);
        return "discount";
    }


    @PostMapping("/apply-discount")
    public String applyDiscount(@RequestParam("discount") String discount, RedirectAttributes redirectAttributes) {
        // Check if discount value is already set
        if (discountValue != null) {
            return "redirect:/shoppingcart";
        } else {
            // Set the discount value
            discountValue = discount;
            // Save the discount value as a flash attribute
            redirectAttributes.addFlashAttribute("discount", discountValue);
            // Redirect to shopping cart after saving discount
            return "redirect:/shoppingcart";
        }
    }

}
