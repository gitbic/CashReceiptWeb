package ru.clevertec.cashReceiptWeb.repositories.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.beans.Product;
import ru.clevertec.cashReceiptWeb.repositories.ProductsRepository;
import ru.clevertec.cashReceiptWeb.repositories.mappers.ProductMapper;

import javax.sql.DataSource;
import java.util.List;

//@RequiredAr lombok plugin
@Repository
public class JdbcTemplateProductRepository implements ProductsRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createProduct(Product product) {
        String sqlQuery = "INSERT INTO products (id, name, price, is_discount) VALUES (?,?,?,?)";
        jdbcTemplate.update(sqlQuery, product.getId(), product.getName(), product.getPrice(), product.isDiscount());
    }

    public Product getProduct(int id) {
        String sqlQuery = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sqlQuery, new ProductMapper(), id);
    }

    @Override
    public void removeProduct(int id) {
        String sqlQuery = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }

    @Override
    public void updateProduct(int id, Product product) {
        String sqlQuery = "UPDATE products SET name = ?, price = ?, is_discount = ?, id = ? WHERE id = ?";
        jdbcTemplate.update(sqlQuery, product.getName(), product.getPrice(), product.isDiscount(), product.getId(), id);
    }

    @Override
    public List<Product> listProducts() {
        String sqlQuery = "SELECT * FROM products";
        return jdbcTemplate.query(sqlQuery, new ProductMapper());
    }
}
