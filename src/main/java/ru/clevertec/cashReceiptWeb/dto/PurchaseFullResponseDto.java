package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseFullResponseDto {
    private Long userId;
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private int productNumber;
    private double discount;
    private BigDecimal cost;
}
