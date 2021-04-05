package ru.clevertec.cashReceiptWeb.repository.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.dto.PurchaseDto;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.service.OrderService;
import ru.clevertec.cashReceiptWeb.service.ProductService;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.math.BigDecimal;

@Service
public class PurchaseDtoMapper {

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    public PurchaseDto map(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();
        Product product = productService.findById(purchase.getProductId());

        purchaseDto.setProductName(product.getName());
        purchaseDto.setProductPrice(product.getPrice());
        purchaseDto.setProductNumber(purchase.getProductNumber());
        purchaseDto.setDiscount(product.isDiscount());
        purchaseDto.setCost(orderService.calculatePurchase(purchase));

        return purchaseDto;
    }
}
