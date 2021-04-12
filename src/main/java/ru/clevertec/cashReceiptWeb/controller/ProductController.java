package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.cashReceiptWeb.dto.ProductRequestDto;
import ru.clevertec.cashReceiptWeb.dto.ProductResponseDto;
import ru.clevertec.cashReceiptWeb.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public List<ProductResponseDto> getAllProductsDto() {
        return productService.getAllProductsResponseDto();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductDto(@PathVariable Long id) {
        return productService.getProductResponseDto(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @PostMapping()
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.addProduct(productRequestDto);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@RequestBody ProductRequestDto productRequestDto, @PathVariable Long id) {
        return productService.updateProduct(id, productRequestDto);
    }

}
