package ru.clevertec.cashReceiptWeb.repositories.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.clevertec.cashReceiptWeb.beans.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {


    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setDiscount(rs.getBoolean("is_discount"));

        return product;
    }
}
