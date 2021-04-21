package ru.clevertec.cashReceiptWeb.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.cashReceiptWeb.dto.ProductRequestDto;
import ru.clevertec.cashReceiptWeb.dto.ProductResponseDto;
import ru.clevertec.cashReceiptWeb.service.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductResponseDto> getAllProductsDto() {
        log.info("Method: {}, input value: {}", "getAllProductsDto", "none");

        List<ProductResponseDto> productResponseDtoList = productService.getAllProductsResponseDto();

        log.info("Method: {}, output value: {}", "getAllProductsDto", productResponseDtoList);
        return productResponseDtoList;
    }


    @GetMapping("/{id}")
    public ProductResponseDto getProductDto(@PathVariable Long id) {
        log.info("Method: {}, input value: id = {}", "getProductDto", id);

        ProductResponseDto productResponseDto = productService.getProductResponseDto(id);

        log.info("Method: {}, output value: {}", "getProductDto", productResponseDto);
        return productResponseDto;
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        log.info("Method: {}, input value: id = {}", "deleteProduct", id);

        productService.deleteProductById(id);

        log.info("Method: {}, output value: {}", "deleteProduct", "none");
    }


    @PostMapping()
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) {
        log.info("Method: {}, input value: {}", "addProduct", productRequestDto);

        ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);

        log.info("Method: {}, output value: {}", "addProduct", productResponseDto);
        return productResponseDto;
    }


    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto productRequestDto, @PathVariable Long id) {
        log.info("Method: {}, input values: id = {}, {}", "updateProduct", id, productRequestDto);

        ProductResponseDto productResponseDto = productService.updateProduct(id, productRequestDto);

        log.info("Method: {}, output value: {}", "updateProduct", productResponseDto);
        return productResponseDto;
    }

}
