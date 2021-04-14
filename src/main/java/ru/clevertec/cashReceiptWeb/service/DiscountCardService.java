package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.entity.DiscountCard;

import java.util.List;


public interface DiscountCardService {
    List<DiscountCard> findAllDiscountCards();

    void deleteDiscountCardByCardNumber(String cardNumber);

    DiscountCard findDiscountCardByCardNumber(String cardNumber);

    void saveDiscountCard(DiscountCard discountCard);
}
