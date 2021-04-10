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


//    @Override
//    public void add(DiscountCard discountCard) {
//        String sqlQuery = "INSERT INTO discount_card (card_number, discount) VALUES (?,?)";
//        jdbcTemplate.update(sqlQuery, discountCard.getCardNumber(), discountCard.getDiscount());
//        log.info("Discount card, number {}, has been added to db table {}", discountCard.getCardNumber(), "discount_card");
//    }
//
//    @Override
//    public DiscountCard find(String cardNumber) {
//        String sqlQuery = "SELECT * FROM discount_card WHERE card_number = ?";
//        return jdbcTemplate.queryForObject(sqlQuery, new DiscountCardMapper(), cardNumber);
//    }
//
//    @Override
//    public void delete(String cardNumber) {
//        String sqlQuery = "DELETE FROM discount_card WHERE card_number = ?";
//        jdbcTemplate.update(sqlQuery, cardNumber);
//    }
//
//    @Override
//    public void update(DiscountCard discountCard) {
//        String sqlQuery = "UPDATE discount_card SET discount = ? WHERE card_number = ?";
//        jdbcTemplate.update(sqlQuery, discountCard.getDiscount(), discountCard.getCardNumber());
//    }
//
//    @Override
//    public List<DiscountCard> findAll() {
//        String sqlQuery = "SELECT * FROM discount_card";
//        return jdbcTemplate.query(sqlQuery, new DiscountCardMapper());
//    }