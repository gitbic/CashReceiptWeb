package ru.clevertec.cashReceiptWeb.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.dto.ProductResponseDto;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.exception.UserNotFoundException;
import ru.clevertec.cashReceiptWeb.repository.ProductsRepository;
import ru.clevertec.cashReceiptWeb.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductsRepository productsRepository, ModelMapper modelMapper) {
        this.productsRepository = productsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductResponseDto> getAllProductsResponseDto() {
        return findAllProducts().stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getProductResponseDto(Long id) {
        Product product = findProductById(id).orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.map(product, ProductResponseDto.class);
    }

    public Optional<Product> findProductById(Long id) {
        return productsRepository.findById(id);
    }

    public List<Product> findAllProducts() {
        return productsRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productsRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }
}
