package ru.clevertec.cashReceiptWeb.repositories.jdbcTemplateImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entityes.Product;
import ru.clevertec.cashReceiptWeb.repositories.ProductsRepository;
import ru.clevertec.cashReceiptWeb.repositories.mappers.ProductMapper;

import java.util.List;

@Repository
public class JdbcTemplateProductRepository implements ProductsRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addProduct(Product product) {
        String sqlQuery = "INSERT INTO products (id, name, price, is_discount) VALUES (?,?,?,?)";
        jdbcTemplate.update(sqlQuery, product.getId(), product.getName(), product.getPrice(), product.isDiscount());
    }

    @Override
    public Product getProductById(int id) {
        String sqlQuery = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new ProductMapper(), id);
    }

    @Override
    public void removeProduct(int id) {
        String sqlQuery = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public void updateProduct(Product product) {
        String sqlQuery = "UPDATE products SET name = ?, price = ?, is_discount = ? WHERE id = ?";
        jdbcTemplate.update(sqlQuery, product.getName(), product.getPrice(), product.isDiscount(), product.getId());
    }

    @Override
    public List<Product> getProductList() {
        String sqlQuery = "SELECT * FROM products";
        return jdbcTemplate.query(sqlQuery, new ProductMapper());
    }
}
