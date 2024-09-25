package org.example.transaction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDocumentNumberException extends RuntimeException{
    public InvalidDocumentNumberException(String message) {
        super(message);
    }

}
