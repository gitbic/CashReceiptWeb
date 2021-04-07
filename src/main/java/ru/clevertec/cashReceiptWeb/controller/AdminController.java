package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;
import ru.clevertec.cashReceiptWeb.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    DiscountCardService discountCardService;

    @GetMapping
    public String adminPage(Model model) {
        return "admin/adminPage";
    }

    @GetMapping("/userManager")
    public String userManager(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName());
        DiscountCard discountCard = discountCardService.get(user.getCardNumber());
///!!!!
        model.addAttribute("discountCard", discountCard);
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.findAll());
        return "admin/userManagerPage";
    }

    @GetMapping("/productManager")
    public String productManager(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.findAll());
        return "admin/productManagerPage";
    }
}
