package ru.clevertec.cashReceiptWeb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.clevertec.cashReceiptWeb.constants.ErrMsgFormatStr;

@ResponseStatus(value= HttpStatus.CONFLICT)
public class UsernameExistException extends RuntimeException{
    public UsernameExistException() {
    }

    public UsernameExistException(String username) {
        super(String.format(ErrMsgFormatStr.USERNAME_ALREADY_EXIST, username));
    }
}
