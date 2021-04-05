package ru.clevertec.cashReceiptWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;
import ru.clevertec.cashReceiptWeb.service.ProductService;

@Controller
@RequestMapping("/shop")
public class ShoppingController {

    @Autowired
    ProductService productService;

    @Autowired
    DiscountCardService discountCardService;

    @GetMapping("products")
    public String showProducts(){
        return "/shop/products";
    }
}
