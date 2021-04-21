package ru.clevertec.cashReceiptWeb.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.constants.GlobalConst;
import ru.clevertec.cashReceiptWeb.dto.PurchaseFullResponseDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseRequestDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseSimpleResponseDto;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;
import ru.clevertec.cashReceiptWeb.exception.PurchaseNotFoundException;
import ru.clevertec.cashReceiptWeb.repository.PurchaseRepository;
import ru.clevertec.cashReceiptWeb.service.ProductService;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;


    @Override
    public void deletePurchaseByPurchaseId(PurchaseId purchaseId) {
        log.info("Method: {}, input value: {}", "deletePurchaseByPurchaseId", purchaseId);
        purchaseRepository.deleteById(purchaseId);
        log.info("Method: {}, output value: {}", "deletePurchaseByPurchaseId", "none");
    }


    @Override
    public void deleteAllPurchasesByUserId(Long userId) {
        log.info("Method: {}, input value: userId = {}", "deleteAllPurchasesByUserId", userId);
        purchaseRepository.deleteAllByUserId(userId);
        log.info("Method: {}, output value: {}", "deleteAllPurchasesByUserId", "none");
    }


    @Override
    public List<Purchase> getAllPurchasesByUserId(Long userId) {
        log.info("Method: {}, input value: userId = {}", "getAllPurchasesByUserId", userId);

        List<Purchase> purchases = purchaseRepository.findAllByUserId(userId);

        log.info("Method: {}, output value: {}", "getAllPurchasesByUserId", purchases);
        return purchases;
    }


    @Override
    public PurchaseSimpleResponseDto addPurchase(PurchaseRequestDto purchaseRequestDto) {
        log.info("Method: {}, input value: {}", "addPurchase", purchaseRequestDto);

        Purchase newPurchase = modelMapper.map(purchaseRequestDto, Purchase.class);
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(newPurchase.getPurchaseId());

        if (optionalPurchase.isPresent()) {
            newPurchase.setProductNumber(optionalPurchase.get().getProductNumber() + newPurchase.getProductNumber());
        }

        newPurchase = purchaseRepository.save(newPurchase);
        PurchaseSimpleResponseDto purchaseSimpleResponseDto =
                modelMapper.map(newPurchase, PurchaseSimpleResponseDto.class);

        log.info("Method: {}, output value: {}", "addPurchase", purchaseSimpleResponseDto);
        return purchaseSimpleResponseDto;
    }


    @Override
    public List<PurchaseSimpleResponseDto> getUserPurchasesSimpleResponseDtoList(Long userId) {
        log.info("Method: {}, input value: userId = {}", "getUserPurchasesSimpleResponseDtoList", userId);

        List<PurchaseSimpleResponseDto> purchaseSimpleResponseDtoList = getAllPurchasesByUserId(userId).stream()
                .map(purchase -> modelMapper.map(purchase, PurchaseSimpleResponseDto.class))
                .collect(Collectors.toList());

        log.info("Method: {}, output value: {}", "getUserPurchasesSimpleResponseDtoList", purchaseSimpleResponseDtoList);
        return purchaseSimpleResponseDtoList;
    }


    @Override
    public PurchaseSimpleResponseDto getPurchaseSimpleResponseDto(PurchaseId purchaseId) {
        log.info("Method: {}, input value: {}", "getPurchaseSimpleResponseDto", purchaseId);

        Purchase purchase = findPurchaseByPurchaseId(purchaseId);
        PurchaseSimpleResponseDto purchaseSimpleResponseDto =
                modelMapper.map(purchase, PurchaseSimpleResponseDto.class);

        log.info("Method: {}, output value: {}", "getPurchaseSimpleResponseDto", purchaseSimpleResponseDto);
        return purchaseSimpleResponseDto;
    }


    @Override
    public PurchaseSimpleResponseDto updatePurchase(PurchaseId purchaseId, PurchaseRequestDto purchaseRequestDto) {
        log.info("Method: {}, input values: {}, {}", "updatePurchase", purchaseId, purchaseRequestDto);

        Purchase newPurchase = modelMapper.map(purchaseRequestDto, Purchase.class);
        Purchase purchase = findPurchaseByPurchaseId(purchaseId);

        purchase.toBuilder()
                .productNumber(newPurchase.getProductNumber())
                .build();

        purchase = purchaseRepository.save(purchase);

        PurchaseSimpleResponseDto purchaseSimpleResponseDto =
                modelMapper.map(purchase, PurchaseSimpleResponseDto.class);

        log.info("Method: {}, output value: {}", "updatePurchase", purchaseSimpleResponseDto);
        return purchaseSimpleResponseDto;
    }


    @Override
    public List<PurchaseFullResponseDto> getUserPurchaseFullResponseDtoList(Long userId) {
        log.info("Method: {}, input value: userId = {}", "getUserPurchaseFullResponseDtoList", userId);

        List<Purchase> purchases = getAllPurchasesByUserId(userId);
        List<PurchaseFullResponseDto> purchaseFullResponseDtoList = purchases.stream()
                .map(purchase -> getPurchaseFullResponseDto(purchase.getPurchaseId()))
                .collect(Collectors.toList());

        log.info("Method: {}, output value: {}", "getUserPurchaseFullResponseDtoList", purchaseFullResponseDtoList);
        return purchaseFullResponseDtoList;
    }


    @Override
    public PurchaseFullResponseDto getPurchaseFullResponseDto(PurchaseId purchaseId) {
        log.info("Method: {}, input value: {}", "getPurchaseFullResponseDto", purchaseId);

        Purchase purchase = findPurchaseByPurchaseId(purchaseId);
        Product product = productService.getProductById(purchase.getProductId());

        double discountPercent = product.isDiscount()
                ? GlobalConst.DISCOUNT_PERCENT_FOR_PURCHASE
                : GlobalConst.DISCOUNT_PERCENT_ZERO;

        PurchaseFullResponseDto purchaseFullResponseDto = PurchaseFullResponseDto
                .builder()
                .userId(purchase.getUserId())
                .productId(product.getId())
                .productName(product.getName())
                .productPrice(product.getPrice())
                .productNumber(purchase.getProductNumber())
                .purchaseCost(getPurchaseCost(purchase))
                .discountPercent(discountPercent)
                .build();

        log.info("Method: {}, output value: {}", "getPurchaseFullResponseDto", purchaseFullResponseDto);
        return purchaseFullResponseDto;
    }


    private Purchase findPurchaseByPurchaseId(PurchaseId purchaseId) {
        log.info("Method: {}, input value: {}", "findPurchaseByPurchaseId", purchaseId);

        Purchase purchase = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));

        log.info("Method: {}, output value: {}", "findPurchaseByPurchaseId", purchase);
        return purchase;
    }


    @Override
    public BigDecimal getPurchaseCost(Purchase purchase) {
        log.info("Method: {}, input value: {}", "getPurchaseCost", purchase);

        Product product = productService.getProductById(purchase.getProductId());
        BigDecimal cost = product.getPrice().multiply(BigDecimal.valueOf(purchase.getProductNumber()));

        BigDecimal discount = BigDecimal.ZERO;
        if (product.isDiscount()) {
            discount = cost.multiply(BigDecimal.valueOf(
                    GlobalConst.DISCOUNT_PERCENT_FOR_PURCHASE / GlobalConst.ONE_HUNDRED_PERCENT));
        }

        BigDecimal purchaseCost = cost.subtract(discount);

        log.info("Method: {}, output value: cost = {}", "getPurchaseCost", purchaseCost);
        return purchaseCost;
    }

}
