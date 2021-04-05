package ru.clevertec.cashReceiptWeb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.clevertec.cashReceiptWeb.dto.PurchaseDto;
import ru.clevertec.cashReceiptWeb.entity.Purchase;
import ru.clevertec.cashReceiptWeb.repository.mapper.PurchaseDtoMapper;
import ru.clevertec.cashReceiptWeb.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DtoMapperService {

    @Autowired
    ProductService productService;

    @Autowired
    PurchaseDtoMapper purchaseDtoMapper;


    public List<PurchaseDto> mapToPurchasesDto(List<Purchase> purchases) {
//        PurchaseDtoMapper purchaseDtoMapper = new PurchaseDtoMapper();
        List<PurchaseDto> purchasesDto = new ArrayList<>();

        for (Purchase purchase : purchases) {
            purchasesDto.add(purchaseDtoMapper.map(purchase));
        }
        return purchasesDto;
    }
}
