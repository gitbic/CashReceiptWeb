package ru.clevertec.cashReceiptWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.repository.ProductsRepository;
import ru.clevertec.cashReceiptWeb.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductsRepository productsRepository;

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public void save(Product product) {
        productsRepository.save(product);
    }


    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

}
