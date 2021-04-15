package ru.clevertec.cashReceiptWeb.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.dto.ProductRequestDto;
import ru.clevertec.cashReceiptWeb.dto.ProductResponseDto;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.exception.UserNotFoundException;
import ru.clevertec.cashReceiptWeb.repository.ProductsRepository;
import ru.clevertec.cashReceiptWeb.service.ProductService;

import java.util.List;
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
        return productsRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getProductResponseDto(Long id) {
        Product product = getProductById(id);
        return modelMapper.map(product, ProductResponseDto.class);
    }

    public Product getProductById(Long id) {
        return productsRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product newProduct = modelMapper.map(productRequestDto, Product.class);
        Product product = getProductById(id);

        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setDiscount(newProduct.isDiscount());
        product = productsRepository.save(product);

        return modelMapper.map(product, ProductResponseDto.class);
    }

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Product product = modelMapper.map(productRequestDto, Product.class);
        product = productsRepository.save(product);
        return modelMapper.map(product, ProductResponseDto.class);
    }

}
