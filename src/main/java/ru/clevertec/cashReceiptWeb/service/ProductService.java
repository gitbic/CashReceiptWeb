package ru.clevertec.cashReceiptWeb.service;


import ru.clevertec.cashReceiptWeb.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    void deleteById(Long id);

    Optional<Product> findById(Long id);
}
