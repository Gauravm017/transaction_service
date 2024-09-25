package org.example.transaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidTransactionAmountException extends RuntimeException{
    public InvalidTransactionAmountException(String message) {
        super(message);
    }
}

