package ru.clevertec.cashReceiptWeb.beans;

import lombok.Data;

@Data
public class DiscountCard {
    private String cardNumber;
    private double discount;
}
