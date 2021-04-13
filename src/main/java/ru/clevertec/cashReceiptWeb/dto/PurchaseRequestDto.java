package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;

@Data
public class PurchaseRequestDto {
    private Long userId;
    private Long productId;
    private int productNumber;
}
