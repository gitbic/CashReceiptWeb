package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private boolean isDiscount;
    private double discountPercent;
}
