package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.dto.PurchaseFullResponseDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseRequestDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseSimpleResponseDto;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    Purchase save(Purchase purchase);

    PurchaseSimpleResponseDto addPurchase(PurchaseRequestDto purchaseRequestDto);

    void deleteAllPurchasesByUserId(Long userId);

    Optional<Purchase> findPurchase(PurchaseId purchaseId);

    void deletePurchaseByPurchaseId(PurchaseId purchaseId);

    List<Purchase> findAllPurchasesByUserId(Long userId);

    List<PurchaseSimpleResponseDto> getAllPurchasesSimpleResponseDtoByUserId(Long userId);

    PurchaseFullResponseDto getPurchaseFullResponseDto(Purchase purchase);

    List<PurchaseFullResponseDto> getPurchaseFullResponseDtoList(List<Purchase> purchases);

    List<PurchaseFullResponseDto> getCurrentUserPurchaseFullResponseDtoList();
}
