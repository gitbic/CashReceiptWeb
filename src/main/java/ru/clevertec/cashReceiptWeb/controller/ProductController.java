package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productService.add(product);
        return "redirect:/admin/productManager";
    }


//    private final ProductServiceImpl productService;
//
//    @Autowired
//    public ProductController(ProductServiceImpl productService) {
//        this.productService = productService;
//    }
//
////    @GetMapping
////    public String showProductList(Model model) {
////        Product product = new Product();
////        model.addAttribute("products", productService.findAll());
////        model.addAttribute("product", product);
////        return "products";
////    }
}
