package org.example.validation;

import org.example.TransactionServiceApplication;
import org.example.transaction.dto.TransactionRequestDTO;
import org.example.transaction.exception.AccountNotFoundException;
import org.example.transaction.exception.InvalidOperationTypeException;
import org.example.transaction.exception.InvalidTransactionAmountException;
import org.example.transaction.validation.TransactionRequestValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ActiveProfiles("local")
@SpringBootTest(classes = TransactionServiceApplication.class)
public class TransactionRequestValidatorTest {

    @Autowired
    TransactionRequestValidator transactionRequestValidator;

    @Test
    public void validateCreateTransactionRequest_Failure_AccountException(){
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        Assertions.assertThrows(AccountNotFoundException.class,()-> transactionRequestValidator.validateCreateTransactionRequest(transactionRequestDTO));
    }

    @Test
    public void validateCreateTransactionRequest_Failure_OperationTypeException(){
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAccountId(1L);
        Assertions.assertThrows(InvalidOperationTypeException.class,()-> transactionRequestValidator.validateCreateTransactionRequest(transactionRequestDTO));
    }

    @Test
    public void validateCreateTransactionRequest_Failure_AmountException(){
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAccountId(1L);
        transactionRequestDTO.setOperationTypeId(1L);
        Assertions.assertThrows(InvalidTransactionAmountException.class,()-> transactionRequestValidator.validateCreateTransactionRequest(transactionRequestDTO));
    }

    @Test
    public void validateCreateTransactionRequest_Success(){
        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setAccountId(1L);
        transactionRequestDTO.setOperationTypeId(1L);
        transactionRequestDTO.setAmount(100.00);
        assertDoesNotThrow(()-> transactionRequestValidator.validateCreateTransactionRequest(transactionRequestDTO));
    }

}
