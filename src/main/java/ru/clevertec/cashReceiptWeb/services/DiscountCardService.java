package ru.clevertec.cashReceiptWeb.services;

import ru.clevertec.cashReceiptWeb.entityes.DiscountCard;

import java.util.List;

interface DiscountCardService {
    List<DiscountCard> findAll();

    void add(DiscountCard discountCard);

    void delete(String cardNumber);

    DiscountCard get(String cardNumber);

    void update(DiscountCard discountCard);
}
