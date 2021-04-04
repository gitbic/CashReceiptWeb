package ru.clevertec.cashReceiptWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.repository.DiscountCardRepository;

import java.util.List;

@Service
public class DiscountCardServiceImpl implements DiscountCardService{
    private final DiscountCardRepository discountCardRepository;

    @Autowired
    public DiscountCardServiceImpl(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }

    public List<DiscountCard> findAll() {
        return discountCardRepository.findAll();
    }

    public void add(DiscountCard discountCard) {
        discountCardRepository.add(discountCard);
    }

    public void delete(String cardNumber) {
        discountCardRepository.delete(cardNumber);
    }

    public DiscountCard get(String cardNumber) {
        return discountCardRepository.find(cardNumber);
    }

    public void update(DiscountCard discountCard) {
        discountCardRepository.update(discountCard);
    }
}
