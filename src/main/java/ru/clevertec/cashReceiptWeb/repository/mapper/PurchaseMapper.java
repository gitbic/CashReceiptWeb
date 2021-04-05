package ru.clevertec.cashReceiptWeb.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.clevertec.cashReceiptWeb.entity.Purchase;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PurchaseMapper implements RowMapper<Purchase> {
    @Override
    public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
        Purchase purchase = new Purchase();

        purchase.setPurchaseId(rs.getLong("purchase_id"));
        purchase.setUserId(rs.getLong("user_id"));
        purchase.setProductId(rs.getLong("product_id"));
        purchase.setProductNumber(rs.getInt("product_number"));

        return purchase;
    }
}
