package ru.clevertec.cashReceiptWeb.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.dto.PurchaseFullResponseDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseRequestDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseSimpleResponseDto;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;
import ru.clevertec.cashReceiptWeb.exception.PurchaseNotFoundException;
import ru.clevertec.cashReceiptWeb.repository.PurchaseRepository;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.OrderService;
import ru.clevertec.cashReceiptWeb.service.ProductService;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.clevertec.cashReceiptWeb.constants.GlobalConst.DISCOUNT_PERCENT_FOR_PURCHASE;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, UserService userService, ProductService productService, OrderService orderService, ModelMapper modelMapper) {
        this.purchaseRepository = purchaseRepository;
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    private Purchase findPurchaseByPurchaseId(PurchaseId purchaseId) {
        return purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));
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
    public List<Purchase> findAllPurchasesByUserId(Long userId) {
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
    public List<PurchaseSimpleResponseDto> getAllPurchasesByUserIdSimpleResponseDtoList(Long userId) {
        return findAllPurchasesByUserId(userId).stream()
                .map(purchase -> modelMapper.map(purchase, PurchaseSimpleResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseSimpleResponseDto getPurchaseSimpleResponseDto(PurchaseId purchaseId) {
        Purchase purchase = findPurchaseByPurchaseId(purchaseId);
        return modelMapper.map(purchase, PurchaseSimpleResponseDto.class);
    }

    @Override
    public PurchaseSimpleResponseDto updatePurchase(PurchaseRequestDto purchaseRequestDto) {
        Purchase newPurchase = modelMapper.map(purchaseRequestDto, Purchase.class);
        Purchase purchase = findPurchaseByPurchaseId(newPurchase.getPurchaseId());

        purchase.setProductNumber(newPurchase.getProductNumber());
        purchase = purchaseRepository.save(purchase);

        return modelMapper.map(purchase, PurchaseSimpleResponseDto.class);
    }

    @Override
    public PurchaseFullResponseDto getPurchaseFullResponseDto(Purchase purchase) {
        PurchaseFullResponseDto purchaseFullResponseDto = new PurchaseFullResponseDto();
        Product product = productService.findProductById(purchase.getProductId()).orElseThrow();

        purchaseFullResponseDto.setProductId(product.getId());
        purchaseFullResponseDto.setProductName(product.getName());
        purchaseFullResponseDto.setProductPrice(product.getPrice());
        purchaseFullResponseDto.setProductNumber(purchase.getProductNumber());
        purchaseFullResponseDto.setCost(orderService.getPurchaseCost(purchase));
        if (product.isDiscount()) {
            purchaseFullResponseDto.setDiscount(DISCOUNT_PERCENT_FOR_PURCHASE);
        }

        return purchaseFullResponseDto;
    }

    @Override
    public List<PurchaseFullResponseDto> getPurchaseFullResponseDtoList(List<Purchase> purchases) {
        List<PurchaseFullResponseDto> purchasesDto = new ArrayList<>();

        for (Purchase purchase : purchases) {
            purchasesDto.add(getPurchaseFullResponseDto(purchase));
        }
        return purchasesDto;
    }

    @Override
    public List<PurchaseFullResponseDto> getUserPurchasesFullResponseDtoList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(authentication.getName()).orElseThrow();
        List<Purchase> purchases = findAllPurchasesByUserId(user.getId());

        return getPurchaseFullResponseDtoList(purchases);
    }

}
