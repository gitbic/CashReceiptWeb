package ru.clevertec.cashReceiptWeb.util;

import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.dto.OrderCostDto;
import ru.clevertec.cashReceiptWeb.dto.PurchaseCostViewDto;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class MappingUtil {
    private static final String SIGN_DOLLAR = "$";
    private static final int NUMBER_DECIMAL = 2;
// TODO del
    public PurchaseCostViewDto mapToToPurchaseCostViewDto(OrderCostDto orderCostDto) {
        PurchaseCostViewDto purchaseCostViewDto = new PurchaseCostViewDto();

        purchaseCostViewDto.setTotalCost(priceToString(orderCostDto.getTotalCost()));
        purchaseCostViewDto.setFinalCost(priceToString(orderCostDto.getFinalCost()));
        purchaseCostViewDto.setDiscountCost(priceToString(orderCostDto.getDiscountCost()));

        return purchaseCostViewDto;
    }

    private static String priceToString(BigDecimal value) {
        return SIGN_DOLLAR + value.setScale(NUMBER_DECIMAL, RoundingMode.HALF_UP).toString();
    }
}
