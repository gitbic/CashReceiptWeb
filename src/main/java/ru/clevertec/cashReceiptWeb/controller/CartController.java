package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {


//    @GetMapping("/cart")
//    public String cart(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByUserName(authentication.getName()).orElseThrow();
//
//        DiscountCard discountCard = discountCardService.findDiscountCardByCardNumber(user.getCardNumber()).orElseThrow();
//        List<PurchaseFullResponseDto> purchasesDto = purchaseService.getCurrentUserPurchaseFullResponseDtoList();
//        PurchaseCostDto purchaseCostDto = orderService.getCurrentUserPurchasesCostDto();
//        PurchaseCostViewDto purchaseCostViewDto = mappingUtil.mapToToPurchaseCostViewDto(purchaseCostDto);
//
//        model.addAttribute("purchasesDto", purchasesDto);
//        model.addAttribute("purchaseCostViewDto", purchaseCostViewDto);
//        model.addAttribute("discountCard", discountCard);
//        return "purchase/cartPage";
//    }
//
}
