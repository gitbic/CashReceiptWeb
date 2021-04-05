package ru.clevertec.cashReceiptWeb.repository;

import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entity.Purchase;

import java.util.List;

@Repository
public interface PurchaseRepository {
    void save(Purchase purchase);

    void deleteUserPurchase(Long userId, Long productId);

    void deleteAllByUserId(Long userId);

    List<Purchase> findAllByUserId(Long userId);
}
