package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.entity.Purchase;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    BigDecimal calculatePurchase(Purchase purchase);

    BigDecimal calculateAllPurchases(List<Purchase> purchases);
}
