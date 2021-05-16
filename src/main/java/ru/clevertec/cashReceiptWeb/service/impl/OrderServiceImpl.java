package ru.clevertec.cashReceiptWeb.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.client.OrderClient;
import ru.clevertec.cashReceiptWeb.constants.GlobalConst;
import ru.clevertec.cashReceiptWeb.dto.OrderCostDto;
import ru.clevertec.cashReceiptWeb.dto.OrderDto;
import ru.clevertec.cashReceiptWeb.entity.DiscountCard;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.security.model.User;
import ru.clevertec.cashReceiptWeb.security.service.UserService;
import ru.clevertec.cashReceiptWeb.service.DiscountCardService;
import ru.clevertec.cashReceiptWeb.service.OrderService;
import ru.clevertec.cashReceiptWeb.service.PurchaseService;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final PurchaseService purchaseService;
    private final DiscountCardService discountCardService;
    private final UserService userService;
    private final OrderClient orderClient;


    @Override
    public OrderDto getOrderDto(Long userId) {
        log.info("Method: {}, input value: userId = {}", "getOrderDto", userId);

        User user = userService.getUserById(userId);
        DiscountCard discountCard = discountCardService.getDiscountCardByCardNumber(user.getCardNumber());
        OrderDto orderDto = new OrderDto();

        orderDto.setPurchaseFullResponseDtoList(purchaseService.getUserPurchasesFullResponseDtoList(userId));
        orderDto.setOrderCostDto(getOrderCostDto(userId));
        orderDto.setUsername(user.getUsername());
        orderDto.setDiscountPercentByCard(discountCard.getDiscount());

        log.info("Method: {}, output value: {}", "getOrderDto", orderDto);
        return orderDto;
    }


    @Override
    public String printCashReceipt(Long userId) {
        log.info("Method: {}, input value: userId = {}", "printCashReceipt", userId);

        OrderDto orderDto = getOrderDto(userId);
        String printedCashReceiptUrl = orderClient.printCashReceipt(orderDto);

        log.info("Method: {}, output value: cashReceiptUrl = {}", "printCashReceipt", printedCashReceiptUrl);
        return printedCashReceiptUrl;
    }

    private OrderCostDto getOrderCostDto(Long userId) {
        log.info("Method: {}, input value: userId = {}", "getOrderCostDto", userId);

        User user = userService.getUserById(userId);
        DiscountCard discountCard = discountCardService.getDiscountCardByCardNumber(user.getCardNumber());
        List<Purchase> purchases = purchaseService.getAllPurchasesByUserId(userId);

        BigDecimal totalCost = purchases.stream()
                .map(purchaseService::getPurchaseCost)
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
