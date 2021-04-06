package ru.clevertec.cashReceiptWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.dto.PurchaseDto;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.mapper.PurchaseCostDtoMapper;
import ru.clevertec.cashReceiptWeb.mapper.PurchaseDtoMapper;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.ProductService;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DtoMapperService {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    PurchaseDtoMapper purchaseDtoMapper;

    @Autowired
    PurchaseCostDtoMapper purchaseCostDtoMapper;

    public PurchaseDto getPurchaseDto(Purchase purchase) {
        return purchaseDtoMapper.map(purchase);
    }

    public List<PurchaseDto> getPurchaseDtoList(List<Purchase> purchases) {
        List<PurchaseDto> purchasesDto = new ArrayList<>();

        for (Purchase purchase : purchases) {
            purchasesDto.add(getPurchaseDto(purchase));
        }
        return purchasesDto;
    }

    public List<PurchaseDto> getCurrentUserPurchaseDtoList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName());
        List<Purchase> purchases = purchaseService.findAllByUserId(user.getId());

        return getPurchaseDtoList(purchases);
    }



//    public PurchaseCostDto getPurchaseCostDto(BigDecimal totalCost, BigDecimal finalCost,
//                                              BigDecimal discountCost, double discountPercent) {
//        return purchaseCostDtoMapper.map(totalCost, finalCost, discountCost, discountPercent);
//    }

//    public PurchaseCostDto getUserPurchasesCostDto() {
//
//    }
}
