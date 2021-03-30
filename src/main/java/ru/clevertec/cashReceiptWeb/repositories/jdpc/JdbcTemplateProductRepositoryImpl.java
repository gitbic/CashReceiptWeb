package ru.clevertec.cashReceiptWeb.repositories.jdpc;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.clevertec.cashReceiptWeb.beans.Product;
import ru.clevertec.cashReceiptWeb.repositories.ProductsRepository;
import ru.clevertec.cashReceiptWeb.repositories.mappers.ProductMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcTemplateProductRepositoryImpl implements ProductsRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
