package ru.clevertec.cashReceiptWeb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.clevertec.cashReceiptWeb.entityes.Product;
import ru.clevertec.cashReceiptWeb.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductList(Model model) {
        Product product = new Product();
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("product", product);
        return "products";
    }
}
