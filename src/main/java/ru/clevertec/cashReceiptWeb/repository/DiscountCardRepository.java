package ru.clevertec.cashReceiptWeb.repository;

import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;

import java.util.List;

@Repository
public interface DiscountCardRepository {

    DiscountCard find(String cardNumber);

    void add(DiscountCard discountCard);

    void update(DiscountCard discountCard);

    void delete(String cardNumber);

    List<DiscountCard> findAll();
}
