package ru.clevertec.cashReceiptWeb.repositories.jdbcTemplateImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entityes.DiscountCard;
import ru.clevertec.cashReceiptWeb.repositories.DiscountCardRepository;
import ru.clevertec.cashReceiptWeb.repositories.mapper.DiscountCardMapper;

import java.util.List;

@Slf4j
@Repository
public class JdbcTemplateDiscountCardRepository implements DiscountCardRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateDiscountCardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addCard(DiscountCard discountCard) {
        String sqlQuery = "INSERT INTO discount_card (card_number, discount) VALUES (?,?)";
        jdbcTemplate.update(sqlQuery, discountCard.getCardNumber(), discountCard.getDiscount());
        log.info("Discount card, number {}, has been added to db table {}", discountCard.getCardNumber(), "discount_card");
    }

    @Override
    public DiscountCard getCard(String cardNumber) {
        String sqlQuery = "SELECT * FROM discount_card WHERE card_number = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new DiscountCardMapper(), cardNumber);
    }

    @Override
    public void removeCard(String cardNumber) {
        String sqlQuery = "DELETE FROM discount_card WHERE card_number = ?";
        jdbcTemplate.update(sqlQuery, cardNumber);
    }

    @Override
    public void updateCard(DiscountCard discountCard) {
        String sqlQuery = "UPDATE discount_card SET discount = ? WHERE card_number = ?";
        jdbcTemplate.update(sqlQuery, discountCard.getDiscount(), discountCard.getCardNumber());
    }

    @Override
    public List<DiscountCard> getCardList() {
        String sqlQuery = "SELECT * FROM discount_card";
        return jdbcTemplate.query(sqlQuery, new DiscountCardMapper());
    }
}
