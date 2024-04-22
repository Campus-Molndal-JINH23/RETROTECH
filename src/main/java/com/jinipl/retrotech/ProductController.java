package com.jinipl.retrotech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String showAllProducts(Model model) {
        Iterable<Product> showProducts = productService.showAllProducts();
        model.addAttribute("products", showProducts);
        return "products";
    }

    @GetMapping("/products/search")
    public String searchProducts(@RequestParam(value = "category", required = false) String category,
                                 @RequestParam(value = "name", required = false) String name, Model model) {
        Iterable<Product> products = productService.findByCategoryAndName(category, name);
        model.addAttribute("products", products);
        return "search_results";
    }

    //TODO: Implementera fler metoder


}
