package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.dto.PurchaseFullResponseDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseRequestDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseSimpleResponseDto;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;

import java.math.BigDecimal;
import java.util.List;

public interface PurchaseService {

    PurchaseSimpleResponseDto addPurchase(PurchaseRequestDto purchaseRequestDto);

    void deleteAllPurchasesByUserId(Long userId);

    void deletePurchaseByPurchaseId(PurchaseId purchaseId);

    List<Purchase> getAllPurchasesByUserId(Long userId);

    List<PurchaseSimpleResponseDto> getUserPurchasesSimpleResponseDtoList(Long userId);

    List<PurchaseFullResponseDto> getUserPurchasesFullResponseDtoList(Long userId);

    PurchaseFullResponseDto getPurchaseFullResponseDto(PurchaseId purchaseId);

    PurchaseSimpleResponseDto getPurchaseSimpleResponseDto(PurchaseId purchaseId);

    PurchaseSimpleResponseDto updatePurchase(PurchaseId purchaseId, PurchaseRequestDto purchaseRequestDto);

    BigDecimal getPurchaseCost(Purchase purchase);
}
