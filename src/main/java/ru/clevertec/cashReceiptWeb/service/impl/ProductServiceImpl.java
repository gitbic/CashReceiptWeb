package ru.clevertec.cashReceiptWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.repository.ProductsRepository;
import ru.clevertec.cashReceiptWeb.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductsRepository productsRepository;

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public void add(Product product) {
        productsRepository.add(product);
    }

    public void update(Product product) {
        productsRepository.update(product);
    }

    public void deleteById(Long id) {
        productsRepository.delete(id);
    }

    public Product findById(Long id) {
        return productsRepository.findById(id);
    }

    public Product findByName(String name) {
        return productsRepository.findByName(name);
    }
}
