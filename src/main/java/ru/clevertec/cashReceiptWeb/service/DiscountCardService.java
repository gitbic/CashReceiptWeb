package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.entity.DiscountCard;

import java.util.List;
import java.util.Optional;


public interface DiscountCardService {
    List<DiscountCard> findAllDiscountCards();

    void deleteDiscountCardByCardNumber(String cardNumber);

    Optional<DiscountCard> findDiscountCardByCardNumber(String cardNumber);

    void saveDiscountCard(DiscountCard discountCard);
}
