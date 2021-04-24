package ru.clevertec.cashReceiptWeb.exception;

import ru.clevertec.cashReceiptWeb.constants.ErrMsgFormatStr;

public class DiscountCardNotFoundException extends EntityNotFoundException{
    public DiscountCardNotFoundException() {
    }

    public DiscountCardNotFoundException(String cardNumber) {
        super(String.format(ErrMsgFormatStr.DISCOUNT_CARD_NUMBER_NOT_FOUND, cardNumber));
    }
}
