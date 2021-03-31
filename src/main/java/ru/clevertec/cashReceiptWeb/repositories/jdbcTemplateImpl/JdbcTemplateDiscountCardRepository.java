package ru.clevertec.cashReceiptWeb.repositories.jdbcTemplateImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.beans.DiscountCard;
import ru.clevertec.cashReceiptWeb.repositories.DiscountCardRepository;
import ru.clevertec.cashReceiptWeb.repositories.mappers.DiscountCardMapper;

import java.util.List;

@Repository
public class JdbcTemplateDiscountCardRepository implements DiscountCardRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateDiscountCardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addCard(DiscountCard discountCard) {
        String sqlInsertQuery = "INSERT INTO discount_cards (card_number, discount) VALUES (?,?)";
        jdbcTemplate.update(sqlInsertQuery, discountCard.getCardNumber(), discountCard.getDiscount());
    }

    @Override
    public DiscountCard getCard(String cardNumber) {
        String sqlSelectByIdQuery = "SELECT * FROM discount_cards WHERE card_number = ?";
        return jdbcTemplate.queryForObject(sqlSelectByIdQuery, new DiscountCardMapper(), cardNumber);
    }

    @Override
    public void removeCard(String cardNumber) {
        String sqlDeleteQuery = "DELETE FROM discount_cards WHERE card_number = ?";
        jdbcTemplate.update(sqlDeleteQuery, cardNumber);
    }

    @Override
    public void updateCard(DiscountCard discountCard) {
        String sqlUpdateQuery = "UPDATE discount_cards SET discount = ? WHERE card_number = ?";
        jdbcTemplate.update(sqlUpdateQuery, discountCard.getDiscount(), discountCard.getCardNumber());
    }

    @Override
    public List<DiscountCard> getCardList() {
        String sqlSelectAllQuery = "SELECT * FROM discount_cards";
        return jdbcTemplate.query(sqlSelectAllQuery, new DiscountCardMapper());
    }
}
