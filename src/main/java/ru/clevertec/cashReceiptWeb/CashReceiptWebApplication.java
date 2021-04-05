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

    @Autowired
    PurchaseRepository purchaseRepository;

    @EventListener
    public void onStartListener(ApplicationReadyEvent event) {

        ProductServiceImpl productService = new ProductServiceImpl(productsRepository);
        System.out.println(productService.findById(1L));

        DiscountCardServiceImpl discountCardService = new DiscountCardServiceImpl(discountCardRepository);
        System.out.println(discountCardService.get("2431"));

        System.out.println(userRepository.findById(3L));

        Purchase purchase = new Purchase();
        purchase.setUserId(1L);
        purchase.setProductId(1L);
        purchase.setProductNumber(2);

        purchaseRepository.save(purchase);
    }
}
