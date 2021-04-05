package ru.clevertec.cashReceiptWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.repository.DiscountCardRepository;
import ru.clevertec.cashReceiptWeb.repository.ProductsRepository;
import ru.clevertec.cashReceiptWeb.repository.PurchaseRepository;
import ru.clevertec.cashReceiptWeb.security.repository.UserRepository;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.*;

@SpringBootApplication
public class CashReceiptWebApplication {


    public static void main(String[] args) {
        SpringApplication.run(CashReceiptWebApplication.class, args);
    }

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    DiscountCardService discountCardService;

    @EventListener
    public void onStartListener(ApplicationReadyEvent event) {


        System.out.println(productService.findById(1L));

        System.out.println(discountCardService.get("2431"));

        System.out.println(userService.findById(3L));

//        Purchase purchase = new Purchase();
//        purchase.setUserId(1L);
//        purchase.setProductId(1L);
//        purchase.setProductNumber(2);
//
//        purchaseRepository.save(purchase);

    }
}
