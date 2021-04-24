package ru.clevertec.cashReceiptWeb.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.dto.ProductRequestDto;
import ru.clevertec.cashReceiptWeb.dto.ProductResponseDto;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.exception.ProductNotFoundException;
import ru.clevertec.cashReceiptWeb.repository.ProductsRepository;
import ru.clevertec.cashReceiptWeb.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<ProductResponseDto> getAllProductsResponseDto() {
        log.info("Method: {}, input value: {}", "getAllProductsResponseDto()", "none");

        List<ProductResponseDto> productResponseDtoList = productsRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());

        log.info("Method: {}, output value: {}", "getAllProductsResponseDto()", productResponseDtoList);
        return productResponseDtoList;
    }


    @Override
    public ProductResponseDto getProductResponseDto(Long id) {
        log.info("Method: {}, input value: id = {}", "getProductResponseDto", id);

        Product product = getProductById(id);
        ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);

        log.info("Method: {}, output value: {}", "getProductResponseDto", productResponseDto);
        return productResponseDto;
    }


    @Override
    public Product getProductById(Long id) {
        log.info("Method: {}, input value: id = {}", "getProductById", id);

        Product product = productsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        log.info("Method: {}, output value: {}", "getProductById", product);
        return product;
    }


    @Override
    public void deleteProductById(Long id) {
        log.info("Method: {}, input value: id = {}", "deleteProductById", id);

        productsRepository.deleteById(id);

        log.info("Method: {}, output value: {}", "deleteProductById", "none");
    }


    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        log.info("Method: {}, input values: id = {}, {}", "updateProduct", id, productRequestDto);

        Product newProduct = modelMapper.map(productRequestDto, Product.class);

        Product product = getProductById(id).toBuilder()
                .name(newProduct.getName())
                .price(newProduct.getPrice())
                .isDiscount(newProduct.isDiscount())
                .build();

        product = productsRepository.save(product);
        ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);

        log.info("Method: {}, output value: {}", "updateProduct", productResponseDto);
        return productResponseDto;
    }


    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        log.info("Method: {}, input value: {}", "addProduct", productRequestDto);

        Product product = modelMapper.map(productRequestDto, Product.class);
        product = productsRepository.save(product);

        ProductResponseDto productResponseDto = modelMapper.map(product, ProductResponseDto.class);

        log.info("Method: {}, output value: {}", "addProduct", productResponseDto);
        return productResponseDto;
    }

}
