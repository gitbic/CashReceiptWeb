package ru.clevertec.cashReceiptWeb.exception;

import ru.clevertec.cashReceiptWeb.constants.ErrMsgFormatStr;
import ru.clevertec.cashReceiptWeb.entity.id.PurchaseId;

public class PurchaseNotFoundException extends EntityNotFoundException{
    public PurchaseNotFoundException() {
    }

    public PurchaseNotFoundException(String message) {
        super(message);
    }

    public PurchaseNotFoundException(PurchaseId purchaseId) {
        super(String.format(ErrMsgFormatStr.PURCHASE_ID_NOT_FOUND, purchaseId));
    }
}
