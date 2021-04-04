package ru.clevertec.cashReceiptWeb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.entityes.Product;
import ru.clevertec.cashReceiptWeb.repositories.ProductsRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

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
