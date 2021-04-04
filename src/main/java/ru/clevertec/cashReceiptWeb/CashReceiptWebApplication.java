package ru.clevertec.cashReceiptWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ru.clevertec.cashReceiptWeb.repository.DiscountCardRepository;
import ru.clevertec.cashReceiptWeb.repository.ProductsRepository;
import ru.clevertec.cashReceiptWeb.security.repository.UserRepository;
import ru.clevertec.cashReceiptWeb.service.DiscountCardServiceImpl;
import ru.clevertec.cashReceiptWeb.service.ProductServiceImpl;

@SpringBootApplication
public class CashReceiptWebApplication {


    public static void main(String[] args) {
        SpringApplication.run(CashReceiptWebApplication.class, args);
    }

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    DiscountCardRepository discountCardRepository;

    @Autowired
    UserRepository userRepository;

    @EventListener
    public void onStartListener(ApplicationReadyEvent event) {

        ProductServiceImpl productService = new ProductServiceImpl(productsRepository);
        System.out.println(productService.findById(1L));

        DiscountCardServiceImpl discountCardService = new DiscountCardServiceImpl(discountCardRepository);
        System.out.println(discountCardService.get("2431"));

        System.out.println(userRepository.findById(3L));

    }
}
