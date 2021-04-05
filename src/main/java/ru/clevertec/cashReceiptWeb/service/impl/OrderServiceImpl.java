package ru.clevertec.cashReceiptWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
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
import java.security.Principal;
import java.util.List;

import static ru.clevertec.cashReceiptWeb.constants.GlobalConst.DISCOUNT_PERCENT_FOR_PURCHASE;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    DiscountCardService discountCardService;

    @Override
    public BigDecimal calculatePurchase(Purchase purchase) {
        Product product = productService.findById(purchase.getProductId());
        BigDecimal cost = product.getPrice().multiply(BigDecimal.valueOf(purchase.getProductNumber()));

        BigDecimal discount = BigDecimal.ZERO;
        if (product.isDiscount()) {
            discount = cost.multiply(BigDecimal.valueOf(DISCOUNT_PERCENT_FOR_PURCHASE / 100));
        }

        return cost.subtract(discount);
    }

    @Override
    public BigDecimal calculateAllPurchases(List<Purchase> purchases, Principal principal) {
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());
        DiscountCard discountCard = discountCardService.get(user.getCardNumber());

        BigDecimal cost = BigDecimal.ZERO;

        for (Purchase purchase : purchases) {
            cost = cost.add(calculatePurchase(purchase));
        }

        BigDecimal discount = cost.multiply(BigDecimal.valueOf(discountCard.getDiscount() / 100));

        return cost.subtract(discount);
    }
}
