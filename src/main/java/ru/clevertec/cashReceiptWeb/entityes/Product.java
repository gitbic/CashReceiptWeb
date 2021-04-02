package ru.clevertec.cashReceiptWeb.entityes;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private boolean isDiscount;
}
