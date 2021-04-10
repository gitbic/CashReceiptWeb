package ru.clevertec.cashReceiptWeb.service;


import ru.clevertec.cashReceiptWeb.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    void deleteById(Long id);

    Product findById(Long id);

    List<Product> findByName(String name);
}
