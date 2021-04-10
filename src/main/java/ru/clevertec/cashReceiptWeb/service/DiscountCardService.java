package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.entity.DiscountCard;

import java.util.List;


public interface DiscountCardService {
    List<DiscountCard> findAll();

    void deleteByCardNumber(String cardNumber);

    DiscountCard findByCardNumber(String cardNumber);

    void save(DiscountCard discountCard);
}
