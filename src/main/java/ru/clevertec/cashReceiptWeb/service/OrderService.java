package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.dto.PurchaseCostDto;
import ru.clevertec.cashReceiptWeb.entity.Purchase;

import java.math.BigDecimal;

public interface OrderService {

    BigDecimal getPurchaseCost(Purchase purchase);

    PurchaseCostDto getUserAllPurchasesCostDto(Long userId);
}
