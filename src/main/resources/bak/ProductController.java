package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.service.ProductService;

import static ru.clevertec.cashReceiptWeb.constants.GlobalConst.DISCOUNT_PERCENT_FOR_PURCHASE;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/productManager";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/admin/productManager";
    }

    @GetMapping("/view/{id}")
    public String viewProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.findProductById(id).orElseThrow();
        model.addAttribute("discount", DISCOUNT_PERCENT_FOR_PURCHASE);
        model.addAttribute(product);
        return "product/productViewPage";
    }

    @GetMapping("/edit/{id}")
    public String productManager(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.findProductById(id).orElseThrow();
        model.addAttribute("product", product);
        return "product/productEditPage";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute(value = "product") Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/productManager";
    }

}
