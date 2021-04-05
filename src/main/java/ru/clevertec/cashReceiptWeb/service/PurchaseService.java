package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.entity.Purchase;

import java.util.List;

public interface PurchaseService {
    void save(Purchase purchase);

    void deleteUserPurchase(Purchase purchase);

    void deleteAllByUserId(Long userId);

    List<Purchase> findAllByUserId(Long userId);
}
