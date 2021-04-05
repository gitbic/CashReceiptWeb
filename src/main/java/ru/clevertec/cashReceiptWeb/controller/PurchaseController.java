package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.*;
import ru.clevertec.cashReceiptWeb.service.impl.DtoMapperService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    DiscountCardService discountCardService;

    @Autowired
    DtoMapperService dtoMapperService;

    @GetMapping("/products")
    public String showProducts(Model model) {
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("products", productService.findAll());
        return "/purchase/products";
    }

    @PostMapping("/buy")
    public String buy(@ModelAttribute(value = "purchase") Purchase purchase, Principal principal) {
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());

        purchase.setUserId(user.getId());
        purchaseService.save(purchase);
        return "redirect:/purchase/products";
    }

    @GetMapping("/cart")
    public String cart(Model model, Principal principal) {
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());

        List<Purchase> purchases = purchaseService.findAllByUserId(user.getId());
        model.addAttribute("purchasesDto", dtoMapperService.mapToPurchasesDto(purchases));
        return "/purchase/cart";
    }
}
