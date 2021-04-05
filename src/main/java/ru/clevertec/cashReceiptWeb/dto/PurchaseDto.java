package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseDto {
    private String productName;
    private BigDecimal productPrice;
    private int productNumber;
    private boolean isDiscount;
}
