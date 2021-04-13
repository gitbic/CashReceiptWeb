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
import ru.clevertec.cashReceiptWeb.dto.PurchaseFullResponseDto;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.*;
import ru.clevertec.cashReceiptWeb.util.MappingUtil;

import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    private final ProductService productService;
    private final UserService userService;
    private final PurchaseService purchaseService;
    private final DiscountCardService discountCardService;
    private final OrderService orderService;
    private final MappingUtil mappingUtil;

    @Autowired
    public PurchaseController(ProductService productService, UserService userService,
                              PurchaseService purchaseService, DiscountCardService discountCardService,
                              OrderService orderService, MappingUtil mappingUtil) {
        this.productService = productService;
        this.userService = userService;
        this.purchaseService = purchaseService;
        this.discountCardService = discountCardService;
        this.orderService = orderService;
        this.mappingUtil = mappingUtil;
    }

    @GetMapping("/products")
    public String showProducts(Model model) {
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("products", productService.findAllProducts());
        model.addAttribute("discount", GlobalConst.DISCOUNT_PERCENT_FOR_PURCHASE);
        return "purchase/byProductPage";
    }

    @PostMapping("/buy")
    public String buy(@ModelAttribute(value = "purchase") Purchase purchase) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(authentication.getName()).orElseThrow();

        purchase.setUserId(user.getId());
        purchaseService.save(purchase);
        return "redirect:/purchase/products";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(authentication.getName()).orElseThrow();

        DiscountCard discountCard = discountCardService.findDiscountCardByCardNumber(user.getCardNumber()).orElseThrow();
        List<PurchaseFullResponseDto> purchasesDto = purchaseService.getCurrentUserPurchaseDtoList();
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
        User user = userService.findUserByUserName(authentication.getName()).orElseThrow();
        purchaseService.deletePurchaseByPurchaseId(new PurchaseId(user.getId(), productId));
        return "redirect:/purchase/cart";
    }

}
