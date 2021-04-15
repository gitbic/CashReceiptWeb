package ru.clevertec.cashReceiptWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.cashReceiptWeb.entity.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {

}
