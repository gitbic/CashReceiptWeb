package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public void deleteUser(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

//    @PostMapping()
//    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto) {
//        return userService.addUser(userRequestDto);
//    }
//
//    //  if user not exist ? addUser : error ???
//    @PutMapping("/{id}")
//    public UserResponseDto updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
//        return userService.updateUser(id, userRequestDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUserById(id);
//    }
//

//    @PostMapping("/add")
//    public String addProduct(@ModelAttribute(value = "product") Product product) {
//        productService.save(product);
//        return "redirect:/admin/productManager";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable(value = "id") Long id) {
//        productService.deleteById(id);
//        return "redirect:/admin/productManager";
//    }
//
//    @GetMapping("/view/{id}")
//    public String viewProduct(Model model, @PathVariable(value = "id") Long id) {
//        Product product = productService.findById(id).orElseThrow();
//        model.addAttribute("discount", DISCOUNT_PERCENT_FOR_PURCHASE);
//        model.addAttribute(product);
//        return "product/productViewPage";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String productManager(Model model, @PathVariable(value = "id") Long id) {
//        Product product = productService.findById(id).orElseThrow();
//        model.addAttribute("product", product);
//        return "product/productEditPage";
//    }
//
//    @PostMapping("/update")
//    public String updateProduct(@ModelAttribute(value = "product") Product product) {
//        productService.save(product);
//        return "redirect:/admin/productManager";
//    }

}
