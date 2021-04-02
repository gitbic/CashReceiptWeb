package ru.clevertec.cashReceiptWeb.repositories.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.clevertec.cashReceiptWeb.entityes.DiscountCard;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountCardMapper implements RowMapper<DiscountCard> {

    @Override
    public DiscountCard mapRow(ResultSet rs, int rowNum) throws SQLException {
        DiscountCard discountCard = new DiscountCard();

        discountCard.setCardNumber(rs.getString("card_number"));
        discountCard.setDiscount(rs.getDouble("discount"));

        return discountCard;
    }
}
