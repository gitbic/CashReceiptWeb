package ru.clevertec.cashReceiptWeb.exception;

import ru.clevertec.cashReceiptWeb.constants.ErrMsgFormatStr;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(Long id) {
        super(String.format(ErrMsgFormatStr.USER_ID_NOT_FOUND, id));
    }

    public UserNotFoundException(String name) {
        super(String.format(ErrMsgFormatStr.USER_NAME_NOT_FOUND, name));
    }
}
