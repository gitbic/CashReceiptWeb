package ru.clevertec.cashReceiptWeb.mapper;

import org.springframework.stereotype.Component;
import ru.clevertec.cashReceiptWeb.dto.PurchaseCostDto;

import java.math.BigDecimal;

@Component
public class PurchaseCostDtoMapper {
    public PurchaseCostDto map(BigDecimal totalCost, BigDecimal finalCost,
                               BigDecimal discountCost, double discountPercent) {

        PurchaseCostDto purchaseCostDto = new PurchaseCostDto();
        purchaseCostDto.setTotalCost(totalCost);
        purchaseCostDto.setFinalCost(finalCost);
        purchaseCostDto.setDiscountCost(discountCost);
        purchaseCostDto.setDiscountPercent(discountPercent);

        return purchaseCostDto;
    }
}
