package org.example.transaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CreateTransactionException extends RuntimeException{

    public CreateTransactionException() {
        super("ISSUE IN CREATING THE TRANSACTION");
    }
}
