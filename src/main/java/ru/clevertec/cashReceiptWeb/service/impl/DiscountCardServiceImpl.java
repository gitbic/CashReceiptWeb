package ru.clevertec.cashReceiptWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.repository.DiscountCardRepository;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardRepository discountCardRepository;

    @Autowired
    public DiscountCardServiceImpl(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }

    public List<DiscountCard> findAllDiscountCards() {
        return discountCardRepository.findAll();
    }

    public void saveDiscountCard(DiscountCard discountCard) {
        discountCardRepository.save(discountCard);
    }

    public void deleteDiscountCardByCardNumber(String cardNumber) {
        discountCardRepository.deleteById(cardNumber);
    }

    public Optional<DiscountCard> findDiscountCardByCardNumber(String cardNumber) {
        return discountCardRepository.findById(cardNumber);
    }

}
