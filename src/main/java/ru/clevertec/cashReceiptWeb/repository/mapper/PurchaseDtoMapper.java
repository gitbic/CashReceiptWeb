package ru.clevertec.cashReceiptWeb.repository.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.dto.PurchaseDto;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.service.ProductService;

@Service
public class PurchaseDtoMapper{

    @Autowired
    ProductService productService;

    public PurchaseDto map(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();
        Product product = productService.findById(purchase.getProductId());

        purchaseDto.setProductName(product.getName());
        purchaseDto.setProductNumber(purchase.getProductNumber());

        return purchaseDto;

    }
}
