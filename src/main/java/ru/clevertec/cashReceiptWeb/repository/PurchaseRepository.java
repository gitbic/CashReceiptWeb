package ru.clevertec.cashReceiptWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, PurchaseId> {

    void deleteAllByUserId(Long userId);

    List<Purchase> findAllByUserId(Long userId);

}
