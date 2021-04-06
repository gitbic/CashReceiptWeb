package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.cashReceiptWeb.dto.PurchaseCostDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseDto;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;
import ru.clevertec.cashReceiptWeb.service.OrderService;
import ru.clevertec.cashReceiptWeb.service.ProductService;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;
import ru.clevertec.cashReceiptWeb.service.impl.DtoMapperService;

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

    @Autowired
    OrderService orderService;

    @GetMapping("/products")
    public String showProducts(Model model) {
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("products", productService.findAll());
        return "/purchase/products";
    }

    @PostMapping("/buy")
    public String buy(@ModelAttribute(value = "purchase") Purchase purchase) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName());

        purchase.setUserId(user.getId());
        purchaseService.save(purchase);
        return "redirect:/purchase/products";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        List<PurchaseDto> purchasesDto = dtoMapperService.getCurrentUserPurchaseDtoList();
        PurchaseCostDto  purchasesCostDto = orderService.getCurrentUserPurchasesCostDto();

        model.addAttribute("purchasesCostDto", purchasesCostDto);
        model.addAttribute("purchasesDto", purchasesDto);
        return "/purchase/cart";
    }

    @GetMapping("/delete/{id}")
    public String deletePurchase(@PathVariable(value = "id") Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName());
        purchaseService.deleteUserPurchase(user.getId(), productId);
        return "redirect:/purchase/cart";
    }

}
