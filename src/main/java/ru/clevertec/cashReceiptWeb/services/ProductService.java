package ru.clevertec.cashReceiptWeb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.beans.Product;
import ru.clevertec.cashReceiptWeb.repositories.ProductsRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> getAllProducts() {
        return productsRepository.getProductList();
    }

    public void add(Product product) {
        productsRepository.addProduct(product);
    }

    public void deleteById(int id) {
        productsRepository.removeProduct(id);
    }

    public Product getById(int id) {
        return productsRepository.getProductById(id);
    }

    public void update(Product product) {
        productsRepository.updateProduct(product);
    }
}
