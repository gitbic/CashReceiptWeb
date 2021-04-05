package ru.clevertec.cashReceiptWeb.repository.jdbcTemplateImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.repository.PurchaseRepository;
import ru.clevertec.cashReceiptWeb.repository.mapper.PurchaseMapper;

import java.util.List;

@Repository
public class JdbcTemplatePurchaseRepository implements PurchaseRepository {
    JdbcTemplate jdbcTemplate;

    public JdbcTemplatePurchaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Purchase purchase) {
        String sqlQuery = "INSERT INTO purchase (user_id, product_id, product_number) VALUES (?,?,?)" +
                "ON CONFLICT (user_id, product_id)" +
                "DO UPDATE SET product_number = purchase.product_number + ?";

        jdbcTemplate.update(sqlQuery, purchase.getUserId(), purchase.getProductId(), purchase.getProductNumber(),
                purchase.getProductNumber());
    }

    @Override
    public void deleteUserPurchase(Purchase purchase) {
        String sqlQuery = "DELETE FROM purchase WHERE user_id = ? and product_id = ?";
        jdbcTemplate.update(sqlQuery, purchase.getUserId(), purchase.getProductId());
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        String sqlQuery = "DELETE FROM purchase WHERE user_id = ?";
        jdbcTemplate.update(sqlQuery, userId);
    }

    @Override
    public List<Purchase> findAllByUserId(Long userId) {
        String sqlQuery = "SELECT * FROM purchase WHERE user_id = ?";
        return jdbcTemplate.query(sqlQuery, new PurchaseMapper());
    }
}
