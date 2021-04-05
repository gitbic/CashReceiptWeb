package ru.clevertec.cashReceiptWeb.repository;

import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.entity.Purchase;

import java.util.List;
import java.util.Set;

@Repository
public interface PurchaseRepository {
    void save(Purchase purchase);

    void deleteUserPurchase(Purchase purchase);

    void deleteAllByUserId(Long userId);

    List<Purchase> findAllByUserId(Long userId);
}
