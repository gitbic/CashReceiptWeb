package ru.clevertec.cashReceiptWeb.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PurchaseFullResponseDto {
    private Long userId;
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private int productNumber;
    private double discountPercent;
    private BigDecimal purchaseCost;
}
