package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.web.bind.annotation.*;
import ru.clevertec.cashReceiptWeb.dto.PurchaseRequestDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseSimpleResponseDto;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public PurchaseSimpleResponseDto addPurchase(@RequestBody PurchaseRequestDto purchaseRequestDto) {
        return purchaseService.addPurchase(purchaseRequestDto);
    }

    @PutMapping
    public PurchaseSimpleResponseDto updatePurchase(@RequestBody PurchaseRequestDto purchaseRequestDto) {
        return purchaseService.updatePurchase(purchaseRequestDto);
    }

    @GetMapping("/{userId}")
    public List<PurchaseSimpleResponseDto> getUserAllPurchasesDto(@PathVariable Long userId) {
        return purchaseService.getAllPurchasesByUserIdSimpleResponseDtoList(userId);
    }

    @GetMapping()
    public PurchaseSimpleResponseDto getUserAllPurchasesDto(@RequestBody PurchaseId purchaseId) {
        return purchaseService.getPurchaseSimpleResponseDto(purchaseId);
    }

    @DeleteMapping("/{userId}")
    public void deleteAllPurchasesByUserId(@PathVariable Long userId) {
        purchaseService.deleteAllPurchasesByUserId(userId);
    }

    @DeleteMapping
    public void deletePurchase(@RequestBody PurchaseId purchaseId) {
        purchaseService.deletePurchaseByPurchaseId(purchaseId);
    }



}
