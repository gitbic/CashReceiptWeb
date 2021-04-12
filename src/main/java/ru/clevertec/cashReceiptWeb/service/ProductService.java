package ru.clevertec.cashReceiptWeb.service;


import ru.clevertec.cashReceiptWeb.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    void saveProduct(Product product);

    void deleteProductById(Long id);

    Optional<Product> findProductById(Long id);
}
