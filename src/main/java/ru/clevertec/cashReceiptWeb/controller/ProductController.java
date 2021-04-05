package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productService.deleteById(id);
        return "redirect:/admin/productManager";
    }

    @GetMapping("/view/{id}")
    public String viewProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.findById(id);
        model.addAttribute(product);
        return "/product/productView";
    }

    @GetMapping("/edit/{id}")
    public String productManager(Model model, @PathVariable(value = "id") Long id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "/product/productEdit";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute(value = "product") Product product) {
        productService.update(product);
        System.out.println(product);
        return "redirect:/admin/productManager";
    }



}
