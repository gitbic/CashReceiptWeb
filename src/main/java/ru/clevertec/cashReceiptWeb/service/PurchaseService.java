package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.dto.PurchaseDto;
import ru.clevertec.cashReceiptWeb.entity.Purchase;

import java.util.List;

public interface PurchaseService {
    void save(Purchase purchase);

    void deleteUserPurchase(Long userId, Long productId);

    void deleteAllByUserId(Long userId);

    List<Purchase> findAllByUserId(Long userId);

    PurchaseDto getPurchaseDto(Purchase purchase);

    List<PurchaseDto> getPurchaseDtoList(List<Purchase> purchases);

    List<PurchaseDto> getCurrentUserPurchaseDtoList();
}
