package ru.clevertec.cashReceiptWeb.repositories;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.beans.Product;

import javax.sql.DataSource;
import java.util.List;

@Repository
public interface ProductsRepository {
    void setDataSource(DataSource dataSource);

    void createProduct(Product product);

    Product getProduct(int id);

    void removeProduct(int id);

    void updateProduct(int id, Product product);

    List<Product> listProducts();
}
