package ru.clevertec.cashReceiptWeb.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.clevertec.cashReceiptWeb.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();

            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setDiscount(rs.getBoolean("is_discount"));

            return product;
    }
}
