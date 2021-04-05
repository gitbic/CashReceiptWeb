package ru.clevertec.cashReceiptWeb.repository.jdbcTemplateImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.repository.PurchaseRepository;
import ru.clevertec.cashReceiptWeb.repository.mapper.ProductMapper;

import java.util.List;

public class JdbcTemplatePurchaseRepository implements PurchaseRepository {
    JdbcTemplate jdbcTemplate;

    public JdbcTemplatePurchaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Purchase> findAllByUserId(Long userId) {
        return null;
    }

    @Override
    public void add(Purchase purchase) {

    }

    @Override
    public void update(Purchase purchase) {

    }

    @Override
    public void delete(Long purchaseId) {

    }

    @Override
    public void deleteAllForUser(Long userId) {

    }

    @Override
    public List<Purchase> findAllForUser(Long userId) {
        return null;
    }
}

//    @Override
//    public void add(Product product) {
//        String sqlQuery = "INSERT INTO product (name, price, is_discount) VALUES (?,?,?)";
//        jdbcTemplate.update(sqlQuery, product.getName(), product.getPrice(), product.isDiscount());
//    }
//
//    @Override
//    public Product findById(Long id) {
//        String sqlQuery = "SELECT * FROM product WHERE id = ? ORDER BY id";
//        return jdbcTemplate.queryForObject(sqlQuery, new ProductMapper(), id);
//    }
//
//    @Override
//    public Product findByName(String name) {
//        String sqlQuery = "SELECT * FROM product WHERE name = ? ORDER BY id";
//        return jdbcTemplate.queryForObject(sqlQuery, new ProductMapper(), name);
//    }
//
//    @Override
//    public void delete(Long id) {
//        String sqlQuery = "DELETE FROM product WHERE id = ?";
//        jdbcTemplate.update(sqlQuery, id);
//    }
//
//    @Override
//    public void update(Product product) {
//        String sqlQuery = "UPDATE product SET name = ?, price = ?, is_discount = ? WHERE id = ?";
//        jdbcTemplate.update(sqlQuery, product.getName(), product.getPrice(), product.isDiscount(), product.getId());
//    }
//
//    @Override
//    public List<Product> findAll() {
//        String sqlQuery = "SELECT * FROM product ORDER BY id";
//        return jdbcTemplate.query(sqlQuery, new ProductMapper());
//    }