package ru.clevertec.cashReceiptWeb.repositories;

import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entityes.Product;

import java.util.List;

@Repository
public interface ProductsRepository {

    void addProduct(Product product);

    Product getProductById(Long id);

    void removeProduct(Long id);

    void updateProduct(Product product);

    List<Product> getProductList();
}
