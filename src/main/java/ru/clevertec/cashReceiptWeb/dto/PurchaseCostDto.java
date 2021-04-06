package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseCostDto {
    private BigDecimal totalCost;
    private BigDecimal finalCost;
    private BigDecimal discountCost;
}
