package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;

@Data
public class PurchaseSimpleResponseDto {
    private Long userId;
    private Long productId;
    private int productNumber;
    private double discountPercent;
}
