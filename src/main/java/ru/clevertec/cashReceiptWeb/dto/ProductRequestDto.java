package ru.clevertec.cashReceiptWeb.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class ProductRequestDto {

    private String name;
    private BigDecimal price;
    private boolean isDiscount;
}
