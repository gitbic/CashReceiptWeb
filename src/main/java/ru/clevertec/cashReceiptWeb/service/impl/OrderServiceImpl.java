package ru.clevertec.cashReceiptWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.service.OrderService;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    PurchaseService purchaseService;

    @Override
    public BigDecimal calculatePurchase(Purchase purchase) {
        return null;
    }

    @Override
    public BigDecimal calculateAllPurchases(List<Purchase> purchases) {
        return null;
    }
}
