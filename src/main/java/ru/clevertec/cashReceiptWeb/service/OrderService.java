package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.dto.PurchaseCostDto;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.entity.Purchase;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    BigDecimal getPurchaseCost(Purchase purchase);

    PurchaseCostDto getAllPurchasesCostDto(List<Purchase> purchases, DiscountCard discountCard);

    PurchaseCostDto getUserAllPurchasesCostDto(Long userId);
}
