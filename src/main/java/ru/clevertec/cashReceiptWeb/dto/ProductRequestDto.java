package ru.clevertec.cashReceiptWeb.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {

    private String name;
    private BigDecimal price;
    private boolean isDiscount;
}
