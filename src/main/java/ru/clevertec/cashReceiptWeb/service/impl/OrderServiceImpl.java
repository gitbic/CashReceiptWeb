package ru.clevertec.cashReceiptWeb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.constants.GlobalConst;
import ru.clevertec.cashReceiptWeb.dto.OrderDto;
import ru.clevertec.cashReceiptWeb.dto.OrderCostDto;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.entity.Product;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;
import ru.clevertec.cashReceiptWeb.service.OrderService;
import ru.clevertec.cashReceiptWeb.service.ProductService;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final PurchaseService purchaseService;
    private final ProductService productService;
    private final DiscountCardService discountCardService;
    private final UserService userService;

    public OrderServiceImpl(PurchaseService purchaseService, ProductService productService,
                            DiscountCardService discountCardService, UserService userService) {
        this.purchaseService = purchaseService;
        this.productService = productService;
        this.discountCardService = discountCardService;
        this.userService = userService;
    }

    @Override
    public OrderDto getOrderDto(Long userId) {
        log.info("Method: {}, input value: userId = {}", "getOrderDto", userId);

        User user = userService.getUserById(userId);
        DiscountCard discountCard = discountCardService.getDiscountCardByCardNumber(user.getCardNumber());
        OrderDto orderDto = new OrderDto();

        orderDto.setPurchaseFullResponseDtoList(purchaseService.getUserPurchaseFullResponseDtoList(userId));
        orderDto.setOrderCostDto(getOrderCostDto(userId));
        orderDto.setUsername(user.getUsername());
        orderDto.setDiscountPercentByCard(discountCard.getDiscount());

        log.info("Method: {}, output value: {}", "getOrderDto", orderDto);
        return orderDto;
    }

    @Override
    public BigDecimal getPurchaseCost(Purchase purchase) {
        log.info("Method: {}, input value: {}", "getPurchaseCost", purchase);

        Product product = productService.getProductById(purchase.getProductId());
        BigDecimal cost = product.getPrice().multiply(BigDecimal.valueOf(purchase.getProductNumber()));

        BigDecimal discount = BigDecimal.ZERO;
        if (product.isDiscount()) {
            discount = cost.multiply(BigDecimal.valueOf(
                    GlobalConst.DISCOUNT_PERCENT_FOR_PURCHASE / GlobalConst.ONE_HUNDRED_PERCENT));
        }

        BigDecimal purchaseCost = cost.subtract(discount);

        log.info("Method: {}, output value: cost = {}", "getPurchaseCost", purchaseCost);
        return purchaseCost;
    }

    private OrderCostDto getOrderCostDto(Long userId) {
        log.info("Method: {}, input value: userId = {}", "getOrderCostDto", userId);

        User user = userService.getUserById(userId);
        DiscountCard discountCard = discountCardService.getDiscountCardByCardNumber(user.getCardNumber());
        List<Purchase> purchases = purchaseService.getAllPurchasesByUserId(userId);

        BigDecimal totalCost = purchases.stream()
                .map(this::getPurchaseCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal discountCost = totalCost.multiply(BigDecimal.valueOf(
                discountCard.getDiscount() / GlobalConst.ONE_HUNDRED_PERCENT));

        BigDecimal finalCost = totalCost.subtract(discountCost);

        OrderCostDto orderCostDto = new OrderCostDto();
        orderCostDto.setTotalCost(totalCost);
        orderCostDto.setFinalCost(finalCost);
        orderCostDto.setDiscountCost(discountCost);

        log.info("Method: {}, output value: {}", "getOrderCostDto", orderCostDto);
        return orderCostDto;
    }
}
