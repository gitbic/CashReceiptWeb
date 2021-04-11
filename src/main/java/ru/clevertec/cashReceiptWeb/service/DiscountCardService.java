package ru.clevertec.cashReceiptWeb.service;

import ru.clevertec.cashReceiptWeb.entity.DiscountCard;

import java.util.List;
import java.util.Optional;


public interface DiscountCardService {
    List<DiscountCard> findAll();

    void deleteByCardNumber(String cardNumber);

    Optional<DiscountCard> findByCardNumber(String cardNumber);

    void save(DiscountCard discountCard);
}
