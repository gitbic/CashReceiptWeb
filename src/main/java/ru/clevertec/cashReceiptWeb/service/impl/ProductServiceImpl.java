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

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAllProducts() {
        return productsRepository.findAll();
    }

    public void saveProduct(Product product) {
        productsRepository.save(product);
    }


    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }

    public Optional<Product> findProductById(Long id) {
        return productsRepository.findById(id);
    }

}
