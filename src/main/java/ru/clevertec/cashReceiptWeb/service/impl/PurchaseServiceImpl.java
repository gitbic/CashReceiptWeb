package ru.clevertec.cashReceiptWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.dto.PurchaseDto;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;
import ru.clevertec.cashReceiptWeb.repository.PurchaseRepository;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.OrderService;
import ru.clevertec.cashReceiptWeb.service.ProductService;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.util.ArrayList;
import java.util.List;

import static ru.clevertec.cashReceiptWeb.constants.GlobalConst.DISCOUNT_PERCENT_FOR_PURCHASE;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, UserService userService,
                               ProductService productService, OrderService orderService) {
        this.purchaseRepository = purchaseRepository;
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
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
    public PurchaseDto getPurchaseDto(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();
        Product product = productService.findProductById(purchase.getProductId()).orElseThrow();

        purchaseDto.setProductId(product.getId());
        purchaseDto.setProductName(product.getName());
        purchaseDto.setProductPrice(product.getPrice());
        purchaseDto.setProductNumber(purchase.getProductNumber());
        purchaseDto.setCost(orderService.getPurchaseCost(purchase));
        if (product.isDiscount()) {
            purchaseDto.setDiscount(DISCOUNT_PERCENT_FOR_PURCHASE);
        }

        return purchaseDto;
    }

    @Override
    public List<PurchaseDto> getPurchaseDtoList(List<Purchase> purchases) {
        List<PurchaseDto> purchasesDto = new ArrayList<>();

        for (Purchase purchase : purchases) {
            purchasesDto.add(getPurchaseDto(purchase));
        }
        return purchasesDto;
    }

    @Override
    public List<PurchaseDto> getCurrentUserPurchaseDtoList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(authentication.getName()).orElseThrow();
        List<Purchase> purchases = findAllPurchasesByUserId(user.getId());

        return getPurchaseDtoList(purchases);
    }
}
