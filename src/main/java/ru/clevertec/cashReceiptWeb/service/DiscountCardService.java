package ru.clevertec.cashReceiptWeb.service;

import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;

import java.util.List;


public interface DiscountCardService {
    List<DiscountCard> findAll();

    void add(DiscountCard discountCard);

    void delete(String cardNumber);

    DiscountCard get(String cardNumber);

    void update(DiscountCard discountCard);
}
