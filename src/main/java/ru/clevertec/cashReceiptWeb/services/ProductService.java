package ru.clevertec.cashReceiptWeb.services;

import ru.clevertec.cashReceiptWeb.entityes.Product;

import java.util.List;

interface ProductService {
    List<Product> findAll();

    void add(Product product);

    void update(Product product);

    void deleteById(Long id);

    Product findById(Long id);

    Product findByName(String name);
}
