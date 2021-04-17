package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderCostDto {
    private BigDecimal totalCost;
    private BigDecimal finalCost;
    private BigDecimal discountCost;
    private double discountPercentByCard;
}
