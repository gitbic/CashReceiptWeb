package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProductService productService;

    @GetMapping
    public String adminPage(Model model) {
        return "admin/adminPage";
    }

    @GetMapping ("/userManager")
    public String userManager() {
        return "admin/userManager";
    }

    @GetMapping ("/productManager")
    public String productManager(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("products", productService.findAll());

//        boolean discount = false;
//        model.addAttribute("discount", discount);

        return "admin/productManager";
    }
}
