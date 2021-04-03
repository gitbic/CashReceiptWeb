package ru.clevertec.cashReceiptWeb.repositories.jdbcTemplateImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entityes.Product;
import ru.clevertec.cashReceiptWeb.repositories.ProductsRepository;
import ru.clevertec.cashReceiptWeb.repositories.mapper.ProductMapper;

import java.util.List;

@Repository
public class JdbcTemplateProductRepository implements ProductsRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addProduct(Product product) {
        String sqlQuery = "INSERT INTO product (id, name, price, is_discount) VALUES (?,?,?,?)";
        jdbcTemplate.update(sqlQuery, product.getId(), product.getName(), product.getPrice(), product.isDiscount());
    }

    @Override
    public Product getProductById(Long id) {
        String sqlQuery = "SELECT * FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new ProductMapper(), id);
    }

    @Override
    public void removeProduct(Long id) {
        String sqlQuery = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public void updateProduct(Product product) {
        String sqlQuery = "UPDATE product SET name = ?, price = ?, is_discount = ? WHERE id = ?";
        jdbcTemplate.update(sqlQuery, product.getName(), product.getPrice(), product.isDiscount(), product.getId());
    }

    @Override
    public List<Product> getProductList() {
        String sqlQuery = "SELECT * FROM product";
        return jdbcTemplate.query(sqlQuery, new ProductMapper());
    }
}
