package org.example.transaction.validation;

import org.example.transaction.Utils.FlowType;
import org.example.transaction.dto.TransactionRequestDTO;
import org.example.transaction.exception.AccountNotFoundException;
import org.example.transaction.exception.InvalidOperationTypeException;
import org.example.transaction.exception.InvalidTransactionAmountException;
import org.springframework.stereotype.Component;

@Component
public class TransactionRequestValidator extends BaseValidator {

    @Override
    public boolean handle(final String flowType) {
        return FlowType.TRANSACTION.name().equalsIgnoreCase(flowType);
    }

    @Override
    public void validateCreateTransactionRequest(TransactionRequestDTO transactionRequestDTO){

        if(transactionRequestDTO.getAccountId() == null){
            throw new AccountNotFoundException("ACCOUNT ID NOT PROVIDED");
        }
        else if(transactionRequestDTO.getAccountId() <= 0){
            throw new AccountNotFoundException("INVALID ACCOUNT ID");
        }
        else if(transactionRequestDTO.getOperationTypeId() == null){
            throw new InvalidOperationTypeException("OPERATION TYPE NOT PROVIDED");
        }
        else if(transactionRequestDTO.getOperationTypeId() <= 0L){
            throw new InvalidOperationTypeException("INVALID OPERATION TYPE");
        }
        else if(transactionRequestDTO.getAmount() == null){
            throw new InvalidTransactionAmountException("AMOUNT NOT PROVIDED");
        }
        else if(transactionRequestDTO.getAmount() <= 0L){
            throw new InvalidTransactionAmountException("INVALID AMOUNT PROVIDED");
        }
    }
}
