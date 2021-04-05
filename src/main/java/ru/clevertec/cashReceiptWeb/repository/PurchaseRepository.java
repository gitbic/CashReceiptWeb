package ru.clevertec.cashReceiptWeb.repository;

import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.entity.Purchase;

import java.util.List;
import java.util.Set;

@Repository
public interface PurchaseRepository {
    List<Purchase> findAllByUserId(Long userId);

    void add(Purchase purchase);

    void update(Purchase purchase);

    void delete(Long purchaseId);

    void deleteAllForUser(Long userId);

    List<Purchase> findAllForUser(Long userId);
}
