package com.jinipl.retrotech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class ProductController {


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String showAllProducts(Model model) {
        List<Product> showProducts = productService.showAllProducts();
        model.addAttribute("products", showProducts);
        return "products";
    }

    @GetMapping("/products/search")
    public String searchProducts(@RequestParam(value = "category", required = false) String category,
                                 Model model) {
        List<Product> searchResults = productService.findProductsByCategory(category);
        model.addAttribute("searchResults", searchResults);
        return "search_results";
    }
}
