package ru.clevertec.cashReceiptWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.repository.PurchaseRepository;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    @Override
    public void deleteUserPurchase(Purchase purchase) {
        purchaseRepository.deleteUserPurchase(purchase);
    }

    @Override
    public void deleteAllByUserId(Long userId) {
        purchaseRepository.deleteAllByUserId(userId);
    }

    @Override
    public List<Purchase> findAllByUserId(Long userId) {
        return purchaseRepository.findAllByUserId(userId);
    }
}
