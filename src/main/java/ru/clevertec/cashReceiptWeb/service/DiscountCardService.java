package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.entity.DiscountCard;

import java.util.List;


public interface DiscountCardService {
    List<DiscountCard> getAllDiscountCards();

    void deleteDiscountCardByCardNumber(String cardNumber);

    DiscountCard getDiscountCardByCardNumber(String cardNumber);

    void saveDiscountCard(DiscountCard discountCard);
}
