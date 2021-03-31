package ru.clevertec.cashReceiptWeb.repositories.jdbcTemplateImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.beans.Product;
import ru.clevertec.cashReceiptWeb.repositories.ProductsRepository;
import ru.clevertec.cashReceiptWeb.repositories.mappers.ProductMapper;

import java.util.List;

@Repository
public class JdbcTemplateProductRepository implements ProductsRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String sqlInsertQuery = "INSERT INTO products (id, name, price, is_discount) VALUES (?,?,?,?)";
    private final String sqlSelectByIdQuery = "SELECT * FROM products WHERE id = ?";
    private final String sqlDeleteQuery = "DELETE FROM products WHERE id = ?";
    private final String sqlUpdateQuery = "UPDATE products SET name = ?, price = ?, is_discount = ? WHERE id = ?";
    private final String sqlSelectAllQuery = "SELECT * FROM products";

    public JdbcTemplateProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addProduct(Product product) {
        jdbcTemplate.update(sqlInsertQuery, product.getId(), product.getName(), product.getPrice(), product.isDiscount());
    }

    @Override
    public Product getProductById(int id) {
        return jdbcTemplate.queryForObject(sqlSelectByIdQuery, new ProductMapper(), id);
    }

    @Override
    public void removeProduct(int id) {
        jdbcTemplate.update(sqlDeleteQuery, id);
    }

    @Override
    public void updateProduct(Product product) {
        jdbcTemplate.update(sqlUpdateQuery, product.getName(), product.getPrice(), product.isDiscount(), product.getId());
    }

    @Override
    public List<Product> getProductList() {
        return jdbcTemplate.query(sqlSelectAllQuery, new ProductMapper());
    }
}
