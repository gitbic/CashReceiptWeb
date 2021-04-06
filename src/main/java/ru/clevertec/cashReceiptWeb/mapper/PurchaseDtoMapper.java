package ru.clevertec.cashReceiptWeb.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.cashReceiptWeb.dto.PurchaseDto;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.service.OrderService;
import ru.clevertec.cashReceiptWeb.service.ProductService;

import static ru.clevertec.cashReceiptWeb.constants.GlobalConst.DISCOUNT_PERCENT_FOR_PURCHASE;

@Component
public class PurchaseDtoMapper {

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    public PurchaseDto map(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();
        Product product = productService.findById(purchase.getProductId());

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
}
