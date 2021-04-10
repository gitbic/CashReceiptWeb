package ru.clevertec.cashReceiptWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.repository.DiscountCardRepository;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;

import java.util.List;

@Service
public class DiscountCardServiceImpl implements DiscountCardService {

    @Autowired
    DiscountCardRepository discountCardRepository;

    public List<DiscountCard> findAll() {
        return discountCardRepository.findAll();
    }

    public void save(DiscountCard discountCard) {
        discountCardRepository.save(discountCard);
    }

    public void deleteByCardNumber(String cardNumber) {
        discountCardRepository.deleteById(cardNumber);
    }

    public DiscountCard findByCardNumber(String cardNumber) {
        return discountCardRepository.findById(cardNumber).orElseThrow();
    }

}
