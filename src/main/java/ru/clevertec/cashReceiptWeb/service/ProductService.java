package ru.clevertec.cashReceiptWeb.service;


import ru.clevertec.cashReceiptWeb.dto.ProductRequestDto;
import ru.clevertec.cashReceiptWeb.dto.ProductResponseDto;
import ru.clevertec.cashReceiptWeb.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    List<ProductResponseDto> getAllProductsResponseDto();

    Optional<Product> findProductById(Long id);

    ProductResponseDto getProductResponseDto(Long id);

    Product saveProduct(Product product);

    void deleteProductById(Long id);

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);

    ProductResponseDto addProduct(ProductRequestDto productRequestDto);
}
