package ru.clevertec.cashReceiptWeb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PurchaseRequestDto {
    private Long userId;
    private Long productId;
    private int productNumber;
}
