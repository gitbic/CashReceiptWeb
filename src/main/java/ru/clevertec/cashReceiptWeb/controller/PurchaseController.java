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

    @GetMapping("/{id}")
    public List<PurchaseSimpleResponseDto> getUserAllPurchasesDto(@PathVariable Long id) {
        return purchaseService.getAllPurchasesByUserIdSimpleResponseDtoList(id);
    }

    @RequestMapping(value = "/{userId}/{productId}", method = RequestMethod.GET)
    public PurchaseSimpleResponseDto getPurchaseDto(@PathVariable Long userId, @PathVariable Long productId) {
        PurchaseId purchaseId = new PurchaseId(userId, productId);
        return purchaseService.getPurchaseSimpleResponseDto(purchaseId);
    }

    @DeleteMapping("/{id}")
    public void deleteAllPurchasesByUserId(@PathVariable Long id) {
        purchaseService.deleteAllPurchasesByUserId(id);
    }

    @RequestMapping(value = "/{userId}/{productId}", method = RequestMethod.DELETE)
    public void deletePurchase(@PathVariable Long userId, @PathVariable Long productId) {
        PurchaseId purchaseId = new PurchaseId(userId, productId);
        purchaseService.deletePurchaseByPurchaseId(purchaseId);
    }

}
