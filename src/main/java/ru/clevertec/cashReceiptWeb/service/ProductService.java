package ru.clevertec.cashReceiptWeb.service;


import ru.clevertec.cashReceiptWeb.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void add(Product product);

    void update(Product product);

    void deleteById(Long id);

    Product findById(Long id);

    Product findByName(String name);
}
