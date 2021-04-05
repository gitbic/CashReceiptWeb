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
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;
import ru.clevertec.cashReceiptWeb.service.ProductService;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.security.Principal;

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

    @GetMapping("/products")
    public String showProducts(Model model){
        Purchase purchase = new Purchase();
        model.addAttribute("purchase", purchase);
        model.addAttribute("products", productService.findAll());
        return "/purchase/products";
    }

    @PostMapping("/buy")
    public String buy(@ModelAttribute(value = "purchase") Purchase purchase, Principal principal) {
//        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
//        User user = userService.findByUserName(userDetails.getUsername());
//        purchase.setUserId(user.getId());
        purchase.setUserId(1L);
        System.out.println(purchase);
        purchaseService.save(purchase);


        return "redirect:/purchase/products";
    }
}


//    @PostMapping("/add")
//    public String addProduct(@ModelAttribute(value = "product") Product product) {
//        productService.add(product);
//        return "redirect:/admin/productManager";
//    }