package org.example.transaction.validation;

import org.example.transaction.dto.AccountRequestDTO;
import org.example.transaction.dto.TransactionRequestDTO;

public abstract class BaseValidator {

    protected CommonValidation commonValidation = CommonValidation.getInstance();

    public abstract boolean handle(String flowType);

    public void validateAccountAdditionRequest(AccountRequestDTO accountRequestDTO){
    }

    public void validateCreateTransactionRequest(TransactionRequestDTO transactionRequestDTO){
    }
}
