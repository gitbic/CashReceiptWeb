package ru.clevertec.cashReceiptWeb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.beans.DiscountCard;
import ru.clevertec.cashReceiptWeb.repositories.DiscountCardRepository;

import java.util.List;

@Service
public class DiscountCardService {
    private final DiscountCardRepository discountCardRepository;

    @Autowired
    public DiscountCardService(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }

    public List<DiscountCard> getAllCards() {
        return discountCardRepository.getCardList();
    }

    public void add(DiscountCard discountCard) {
        discountCardRepository.addCard(discountCard);
    }

    public void delete(String cardNumber) {
        discountCardRepository.removeCard(cardNumber);
    }

    public DiscountCard get(String cardNumber) {
        return discountCardRepository.getCard(cardNumber);
    }

    public void update(DiscountCard discountCard) {
        discountCardRepository.updateCard(discountCard);
    }
}
