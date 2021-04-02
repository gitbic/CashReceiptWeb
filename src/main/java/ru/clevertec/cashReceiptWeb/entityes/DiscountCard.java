package ru.clevertec.cashReceiptWeb.entityes;

import lombok.Data;

@Data
public class DiscountCard {
    private String cardNumber;
    private double discount;
}
