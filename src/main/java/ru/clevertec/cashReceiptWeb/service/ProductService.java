package ru.clevertec.cashReceiptWeb.service;


import ru.clevertec.cashReceiptWeb.dto.ProductRequestDto;
import ru.clevertec.cashReceiptWeb.dto.ProductResponseDto;
import ru.clevertec.cashReceiptWeb.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProductsResponseDto();

    Product getProductById(Long id);

    ProductResponseDto getProductResponseDto(Long id);

    void deleteProductById(Long id);

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);

    ProductResponseDto addProduct(ProductRequestDto productRequestDto);
}
