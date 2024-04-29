package com.jinipl.retrotech.controller;

import com.jinipl.retrotech.model.Product;
import com.jinipl.retrotech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAllProducts(Model model) {
        List<Product> showProducts = productService.showAllProducts();
        model.addAttribute("products", showProducts);
        return "products";
    }
    @GetMapping("/product/{id}")
    public String showProduct(@PathVariable String id, Model model) {
        Product product = productService.findProductById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
        model.addAttribute("product", product);
        return "product_page";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(value = "category", required = false) String category,
                                 Model model) {
        List<Product> searchResults = productService.findProductsByCategory(category);
        model.addAttribute("searchResults", searchResults);
        return "search_results";
    }
}
