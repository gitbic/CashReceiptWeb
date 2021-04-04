package ru.clevertec.cashReceiptWeb.repository;

import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entity.Product;

import java.util.List;

@Repository
public interface ProductsRepository {

    Product findById(Long id);

    Product findByName(String name);

    void add(Product product);

    void update(Product product);

    void delete(Long id);

    List<Product> findAll();
}
