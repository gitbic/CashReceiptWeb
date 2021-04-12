package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.dto.PurchaseDto;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;

import java.util.List;

public interface PurchaseService {
    void save(Purchase purchase);

    void deleteAllPurchasesByUserId(Long userId);

    void deletePurchaseByPurchaseId(PurchaseId purchaseId);

    List<Purchase> findAllPurchasesByUserId(Long userId);

    PurchaseDto getPurchaseDto(Purchase purchase);

    List<PurchaseDto> getPurchaseDtoList(List<Purchase> purchases);

    List<PurchaseDto> getCurrentUserPurchaseDtoList();
}
