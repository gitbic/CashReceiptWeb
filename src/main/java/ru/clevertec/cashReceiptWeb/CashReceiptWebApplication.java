package ru.clevertec.cashReceiptWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.clevertec.cashReceiptWeb.repositories.ProductsRepository;
import ru.clevertec.cashReceiptWeb.repositories.jdbc.JdbcTemplateProductRepository;
import ru.clevertec.cashReceiptWeb.services.ProductService;

@SpringBootApplication
public class CashReceiptWebApplication {


    public static void main(String[] args) {
        SpringApplication.run(CashReceiptWebApplication.class, args);


        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/cash_receipt_web");
        dataSource.setUsername("postgres");
        dataSource.setPassword("admin");

        ProductsRepository productsRepository = new JdbcTemplateProductRepository();
        productsRepository.setDataSource(dataSource);
        ProductService productService = new ProductService(productsRepository);

        System.out.println(productService.getById(28));
    }

}
