package ru.clevertec.cashReceiptWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ru.clevertec.cashReceiptWeb.repositories.DiscountCardRepository;
import ru.clevertec.cashReceiptWeb.repositories.ProductsRepository;
import ru.clevertec.cashReceiptWeb.services.DiscountCardService;
import ru.clevertec.cashReceiptWeb.services.ProductService;

@SpringBootApplication
public class CashReceiptWebApplication {


    public static void main(String[] args) {
        SpringApplication.run(CashReceiptWebApplication.class, args);
    }

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    DiscountCardRepository discountCardRepository;

    @EventListener
    public void onStartListener(ApplicationReadyEvent event) {

        ProductService productService = new ProductService(productsRepository);
        System.out.println(productService.getById(28));

        DiscountCardService discountCardService = new DiscountCardService(discountCardRepository);
        System.out.println(discountCardService.get("2431"));

    }
}
