package ru.clevertec.cashReceiptWeb.exception;

import ru.clevertec.cashReceiptWeb.constants.ErrMsgFormatStr;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long id) {
        super(String.format(ErrMsgFormatStr.PRODUCT_ID_NOT_FOUND, id));
    }
}
