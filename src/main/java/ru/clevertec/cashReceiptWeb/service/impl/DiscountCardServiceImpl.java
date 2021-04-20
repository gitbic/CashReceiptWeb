package ru.clevertec.cashReceiptWeb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.exception.DiscountCardNotFoundException;
import ru.clevertec.cashReceiptWeb.repository.DiscountCardRepository;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;

import java.util.List;

@Slf4j
@Service
public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardRepository discountCardRepository;

    @Autowired
    public DiscountCardServiceImpl(DiscountCardRepository discountCardRepository) {
        this.discountCardRepository = discountCardRepository;
    }


    public List<DiscountCard> getAllDiscountCards() {
        log.info("Method: {}, input value: {}", "getAllDiscountCards", "none");

        List<DiscountCard> discountCards = discountCardRepository.findAll();

        log.info("Method: {}, output value: {}", "getAllDiscountCards", discountCards);
        return discountCards;
    }


    public void saveDiscountCard(DiscountCard discountCard) {
        log.info("Method: {}, input value: {}", "saveDiscountCard", discountCard);
        discountCardRepository.save(discountCard);
        log.info("Method: {}, output value: {}", "saveDiscountCard", "none");
    }


    public void deleteDiscountCardByCardNumber(String cardNumber) {
        log.info("Method: {}, input value: cardNumber = {}", "deleteDiscountCardByCardNumber", cardNumber);
        discountCardRepository.deleteById(cardNumber);
        log.info("Method: {}, output value: {}", "deleteDiscountCardByCardNumber", "none");
    }


    public DiscountCard getDiscountCardByCardNumber(String cardNumber) {
        log.info("Method: {}, input value: cardNumber = {}", "getDiscountCardByCardNumber", cardNumber);

        DiscountCard discountCard = discountCardRepository.findById(cardNumber)
                .orElseThrow(() -> new DiscountCardNotFoundException(cardNumber));

        log.info("Method: {}, output value: {}", "getDiscountCardByCardNumber", discountCard);
        return discountCard;
    }

}
