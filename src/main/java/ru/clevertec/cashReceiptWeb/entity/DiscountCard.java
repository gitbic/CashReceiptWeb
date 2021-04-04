package ru.clevertec.cashReceiptWeb.entity;

import lombok.Data;

@Data
public class DiscountCard {
    private String cardNumber;
    private double discount;
}
