package ru.clevertec.cashReceiptWeb.repositories;

import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.beans.Product;

import java.util.List;

@Repository
public interface ProductsRepository {

    void addProduct(Product product);

    Product getProductById(int id);

    void removeProduct(int id);

    void updateProduct(Product product);

    List<Product> getProductList();
}
