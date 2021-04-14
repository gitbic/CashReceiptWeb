package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.dto.PurchaseFullResponseDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseRequestDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseSimpleResponseDto;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;

import java.util.List;

public interface PurchaseService {

    PurchaseSimpleResponseDto addPurchase(PurchaseRequestDto purchaseRequestDto);

    void deleteAllPurchasesByUserId(Long userId);

    void deletePurchaseByPurchaseId(PurchaseId purchaseId);

    List<Purchase> findAllPurchasesByUserId(Long userId);

    List<PurchaseSimpleResponseDto> getAllPurchasesByUserIdSimpleResponseDtoList(Long userId);

    PurchaseFullResponseDto getPurchaseFullResponseDto(Purchase purchase);

    List<PurchaseFullResponseDto> getPurchaseFullResponseDtoList(List<Purchase> purchases);

    List<PurchaseFullResponseDto> getUserPurchasesFullResponseDtoList();

    PurchaseSimpleResponseDto getPurchaseSimpleResponseDto(PurchaseId purchaseId);

    PurchaseSimpleResponseDto updatePurchase(PurchaseRequestDto purchaseRequestDto);
}
