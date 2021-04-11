package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.cashReceiptWeb.constants.GlobalConst;
import ru.clevertec.cashReceiptWeb.dto.PurchaseCostDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseCostViewDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseDto;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.*;

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
    OrderService orderService;

    @Autowired
    MappingUtil mappingUtil;

    @GetMapping("/products")
    public String showProducts(Model model) {
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("discount", GlobalConst.DISCOUNT_PERCENT_FOR_PURCHASE);
        return "purchase/byProductPage";
    }

    @PostMapping("/buy")
    public String buy(@ModelAttribute(value = "purchase") Purchase purchase) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName()).orElseThrow();

        purchase.setUserId(user.getId());
        purchaseService.save(purchase);
        return "redirect:/purchase/products";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName()).orElseThrow();

        DiscountCard discountCard = discountCardService.findByCardNumber(user.getCardNumber()).orElseThrow();
        List<PurchaseDto> purchasesDto = purchaseService.getCurrentUserPurchaseDtoList();
        PurchaseCostDto purchaseCostDto = orderService.getCurrentUserPurchasesCostDto();
        PurchaseCostViewDto purchaseCostViewDto = mappingUtil.mapToToPurchaseCostViewDto(purchaseCostDto);

        model.addAttribute("purchasesDto", purchasesDto);
        model.addAttribute("purchaseCostViewDto", purchaseCostViewDto);
        model.addAttribute("discountCard", discountCard);
        return "purchase/cartPage";
    }

    @GetMapping("/delete/{id}")
    public String deletePurchase(@PathVariable(value = "id") Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName()).orElseThrow();
        purchaseService.deleteByPurchaseId(new PurchaseId(user.getId(), productId));
        return "redirect:/purchase/cart";
    }

}
