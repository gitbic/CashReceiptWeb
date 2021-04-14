package ru.clevertec.cashReceiptWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.constants.GlobalConst;
import ru.clevertec.cashReceiptWeb.dto.PurchaseCostDto;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;
import ru.clevertec.cashReceiptWeb.service.OrderService;
import ru.clevertec.cashReceiptWeb.service.ProductService;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final PurchaseService purchaseService;
    private final ProductService productService;
    private final UserService userService;
    private final DiscountCardService discountCardService;

    @Autowired
    public OrderServiceImpl(@Lazy PurchaseService purchaseService, ProductService productService,
                            UserService userService, DiscountCardService discountCardService) {
        this.purchaseService = purchaseService;
        this.productService = productService;
        this.userService = userService;
        this.discountCardService = discountCardService;
    }

    @Override
    public BigDecimal getPurchaseCost(Purchase purchase) {
        Product product = productService.findProductById(purchase.getProductId());
        BigDecimal cost = product.getPrice().multiply(BigDecimal.valueOf(purchase.getProductNumber()));

        BigDecimal discount = BigDecimal.ZERO;
        if (product.isDiscount()) {
            discount = cost.multiply(BigDecimal.valueOf(
                    GlobalConst.DISCOUNT_PERCENT_FOR_PURCHASE / GlobalConst.ONE_HUNDRED_PERCENT));
        }

        return cost.subtract(discount);
    }

    @Override
    public PurchaseCostDto getPurchasesCostDto(List<Purchase> purchases, DiscountCard discountCard) {

        BigDecimal totalCost = BigDecimal.ZERO;
        for (Purchase purchase : purchases) {
            totalCost = totalCost.add(getPurchaseCost(purchase));
        }

        BigDecimal discount = totalCost.multiply(BigDecimal.valueOf(
                discountCard.getDiscount() / GlobalConst.ONE_HUNDRED_PERCENT));
        BigDecimal finalCost = totalCost.subtract(discount);

        PurchaseCostDto purchaseCostDto = new PurchaseCostDto();
        purchaseCostDto.setTotalCost(totalCost);
        purchaseCostDto.setFinalCost(finalCost);
        purchaseCostDto.setDiscountCost(discount);

        return purchaseCostDto;
    }

    @Override
    public PurchaseCostDto getCurrentUserPurchasesCostDto() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(authentication.getName());

        List<Purchase> purchases = purchaseService.findAllPurchasesByUserId(user.getId());
        DiscountCard discountCard = discountCardService.findDiscountCardByCardNumber(user.getCardNumber());
        return getPurchasesCostDto(purchases, discountCard);
    }

}
