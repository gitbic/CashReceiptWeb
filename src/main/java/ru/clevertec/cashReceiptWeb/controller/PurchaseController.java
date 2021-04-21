package ru.clevertec.cashReceiptWeb.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.cashReceiptWeb.dto.PurchaseFullResponseDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseRequestDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseSimpleResponseDto;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public PurchaseSimpleResponseDto addPurchase(@RequestBody PurchaseRequestDto purchaseRequestDto) {
        log.info("Method: {}, input value: {}", "addPurchase", purchaseRequestDto);

        PurchaseSimpleResponseDto purchaseSimpleResponseDto = purchaseService.addPurchase(purchaseRequestDto);

        log.info("Method: {}, output value: {}", "addPurchase", purchaseSimpleResponseDto);
        return purchaseSimpleResponseDto;
    }


    @PutMapping
    public PurchaseSimpleResponseDto updatePurchase(@RequestBody PurchaseRequestDto purchaseRequestDto,
                                                    @RequestParam Long userId, @RequestParam Long productId) {
        log.info("Method: {}, input values: userId = {}, productId = {}, {}",
                "updatePurchase", userId, productId, purchaseRequestDto);

        PurchaseId purchaseId = new PurchaseId(userId, productId);
        PurchaseSimpleResponseDto purchaseSimpleResponseDto = purchaseService.updatePurchase(purchaseId, purchaseRequestDto);

        log.info("Method: {}, output value: {}", "updatePurchase", purchaseSimpleResponseDto);
        return purchaseSimpleResponseDto;
    }


    @GetMapping("/{userId}")
    public List<PurchaseSimpleResponseDto> getUserAllPurchasesSimpleDto(@PathVariable Long userId) {
        log.info("Method: {}, input value: userId = {}", "getUserAllPurchasesSimpleDto", userId);

        List<PurchaseSimpleResponseDto> userPurchasesSimpleResponseDtoList =
                purchaseService.getUserPurchasesSimpleResponseDtoList(userId);

        log.info("Method: {}, output value: {}", "getUserAllPurchasesSimpleDto", userPurchasesSimpleResponseDtoList);
        return userPurchasesSimpleResponseDtoList;
    }


    @GetMapping()
    public PurchaseSimpleResponseDto getPurchaseSimpleDto(@RequestParam Long userId, @RequestParam Long productId) {
        log.info("Method: {}, input values: userId = {}, productId = {}", "getPurchaseSimpleDto", userId, productId);

        PurchaseId purchaseId = new PurchaseId(userId, productId);
        PurchaseSimpleResponseDto purchaseSimpleResponseDto = purchaseService.getPurchaseSimpleResponseDto(purchaseId);

        log.info("Method: {}, output value: {}", "getPurchaseSimpleDto", purchaseSimpleResponseDto);
        return purchaseSimpleResponseDto;
    }


    @GetMapping("/full")
    public PurchaseFullResponseDto getPurchaseFullDto(@RequestParam Long userId, @RequestParam Long productId) {
        log.info("Method: {}, input values: userId = {}, productId = {}", "getPurchaseFullDto", userId, productId);

        PurchaseId purchaseId = new PurchaseId(userId, productId);
        PurchaseFullResponseDto purchaseFullResponseDto = purchaseService.getPurchaseFullResponseDto(purchaseId);

        log.info("Method: {}, output value: {}", "getPurchaseFullDto", purchaseFullResponseDto);
        return purchaseFullResponseDto;
    }


    @GetMapping("/full/{userId}")
    public List<PurchaseFullResponseDto> getUserAllPurchaseFullDto(@PathVariable Long userId) {
        log.info("Method: {}, input value: userId = {}", "getUserAllPurchaseFullDto", userId);

        List<PurchaseFullResponseDto> userPurchaseFullResponseDtoList =
                purchaseService.getUserPurchaseFullResponseDtoList(userId);

        log.info("Method: {}, output value: {}", "getUserAllPurchaseFullDto", userPurchaseFullResponseDtoList);
        return userPurchaseFullResponseDtoList;
    }


    @DeleteMapping("/{userId}")
    public void deleteAllPurchasesByUserId(@PathVariable Long userId) {
        log.info("Method: {}, input value: userId = {}", "deleteAllPurchasesByUserId", userId);

        purchaseService.deleteAllPurchasesByUserId(userId);

        log.info("Method: {}, output value: {}", "deleteAllPurchasesByUserId", "none");
    }


    @DeleteMapping
    public void deletePurchase(@RequestParam Long userId, @RequestParam Long productId) {
        log.info("Method: {}, input values: userId = {}, productId = {}", "deletePurchase", userId, productId);

        PurchaseId purchaseId = new PurchaseId(userId, productId);
        purchaseService.deletePurchaseByPurchaseId(purchaseId);

        log.info("Method: {}, output value: {}", "deleteAllPurchasesByUserId", "none");
    }

}
