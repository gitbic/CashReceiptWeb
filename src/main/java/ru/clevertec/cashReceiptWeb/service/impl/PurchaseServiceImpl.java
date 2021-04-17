package ru.clevertec.cashReceiptWeb.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
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
import ru.clevertec.cashReceiptWeb.service.OrderService;
import ru.clevertec.cashReceiptWeb.service.ProductService;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductService productService;
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ProductService productService,
                               @Lazy OrderService orderService, ModelMapper modelMapper) {
        this.purchaseRepository = purchaseRepository;
        this.productService = productService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deletePurchaseByPurchaseId(PurchaseId purchaseId) {
        purchaseRepository.deleteById(purchaseId);
    }

    @Override
    public void deleteAllPurchasesByUserId(Long userId) {
        purchaseRepository.deleteAllByUserId(userId);
    }

    @Override
    public List<Purchase> getAllPurchasesByUserId(Long userId) {
        return purchaseRepository.findAllByUserId(userId);
    }

    @Override
    public PurchaseSimpleResponseDto addPurchase(PurchaseRequestDto purchaseRequestDto) {
        Purchase newPurchase = modelMapper.map(purchaseRequestDto, Purchase.class);
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(newPurchase.getPurchaseId());

        if (optionalPurchase.isPresent()) {
            newPurchase.setProductNumber(optionalPurchase.get().getProductNumber() + newPurchase.getProductNumber());
        }

        newPurchase = purchaseRepository.save(newPurchase);
        return modelMapper.map(newPurchase, PurchaseSimpleResponseDto.class);
    }

    @Override
    public List<PurchaseSimpleResponseDto> getUserPurchasesSimpleResponseDtoList(Long userId) {
        return getAllPurchasesByUserId(userId).stream()
                .map(purchase -> modelMapper.map(purchase, PurchaseSimpleResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseSimpleResponseDto getPurchaseSimpleResponseDto(PurchaseId purchaseId) {
        Purchase purchase = findPurchaseByPurchaseId(purchaseId);
        return modelMapper.map(purchase, PurchaseSimpleResponseDto.class);
    }

    @Override
    public PurchaseSimpleResponseDto updatePurchase(PurchaseId purchaseId, PurchaseRequestDto purchaseRequestDto) {
        Purchase newPurchase = modelMapper.map(purchaseRequestDto, Purchase.class);
        Purchase purchase = findPurchaseByPurchaseId(purchaseId);

        purchase.setProductNumber(newPurchase.getProductNumber());
        purchase = purchaseRepository.save(purchase);

        return modelMapper.map(purchase, PurchaseSimpleResponseDto.class);
    }

    @Override
    public List<PurchaseFullResponseDto> getUserPurchasesFullResponseDtoList(Long userId) {
        List<Purchase> purchases = getAllPurchasesByUserId(userId);
        return purchases.stream()
                .map(purchase -> getPurchaseFullResponseDto(purchase.getPurchaseId()))
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseFullResponseDto getPurchaseFullResponseDto(PurchaseId purchaseId) {
        PurchaseFullResponseDto purchaseFullResponseDto = new PurchaseFullResponseDto();
        Purchase purchase = findPurchaseByPurchaseId(purchaseId);
        Product product = productService.getProductById(purchase.getProductId());

        purchaseFullResponseDto.setUserId(purchase.getUserId());
        purchaseFullResponseDto.setProductId(product.getId());
        purchaseFullResponseDto.setProductName(product.getName());
        purchaseFullResponseDto.setProductPrice(product.getPrice());
        purchaseFullResponseDto.setProductNumber(purchase.getProductNumber());
        purchaseFullResponseDto.setCost(orderService.getPurchaseCost(purchase));

        double discountPercent = product.isDiscount()
                ? GlobalConst.DISCOUNT_PERCENT_FOR_PURCHASE
                : GlobalConst.DISCOUNT_PERCENT_ZERO;

        purchaseFullResponseDto.setDiscountPercent(discountPercent);

        return purchaseFullResponseDto;
    }

    private Purchase findPurchaseByPurchaseId(PurchaseId purchaseId) {
        return purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));
    }

}
