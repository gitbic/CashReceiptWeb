package ru.clevertec.cashReceiptWeb.repositories;

import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entityes.DiscountCard;

import java.util.List;

@Repository
public interface DiscountCardRepository {
    void addCard(DiscountCard discountCard);

    DiscountCard getCard(String cardNumber);

    void removeCard(String cardNumber);

    void updateCard(DiscountCard discountCard);

    List<DiscountCard> getCardList();
}
