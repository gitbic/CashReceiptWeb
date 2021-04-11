package ru.clevertec.cashReceiptWeb.entity;

import lombok.Data;

@Data
public class Purchase {
    private Long userId;
    private Long productId;
    private Integer productNumber;
}
