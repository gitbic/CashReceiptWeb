package ru.clevertec.cashReceiptWeb.service;


import ru.clevertec.cashReceiptWeb.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    Product saveProduct(Product product);

    void deleteProductById(Long id);

    Optional<Product> findProductById(Long id);
}
