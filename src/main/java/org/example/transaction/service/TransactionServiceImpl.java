package org.example.transaction.service;

import lombok.extern.slf4j.Slf4j;
import org.example.transaction.dto.TransactionRequestDTO;
import org.example.transaction.exception.AccountNotFoundException;
import org.example.transaction.exception.CreateTransactionException;
import org.example.transaction.exception.InvalidOperationTypeException;
import org.example.transaction.model.Account;
import org.example.transaction.model.OperationType;
import org.example.transaction.model.Transaction;
import org.example.transaction.repository.AccountRepository;
import org.example.transaction.repository.OperationTypeRepository;
import org.example.transaction.repository.TransactionRepository;
import org.example.transaction.validation.TransactionRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationTypeRepository operationTypeRepository;

    @Autowired
    private TransactionRequestValidator transactionRequestValidator;

    public Transaction createTransaction(TransactionRequestDTO transactionDTO) {

        transactionRequestValidator.validateCreateTransactionRequest(transactionDTO);

        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        OperationType operationType = operationTypeRepository.findById(transactionDTO.getOperationTypeId())
                .orElseThrow(() -> new InvalidOperationTypeException("Invalid operation type"));

        try{
            Transaction transaction = new Transaction();
            transaction.setAccount(account);
            transaction.setOperationType(operationType);
            transaction.setAmount(transactionDTO.getAmount());
            transaction.setEventDate(LocalDateTime.now());

            return transactionRepository.save(transaction);
        }
        catch (Exception e){
            log.error("Issue in creating the txn request: {}",transactionDTO,e);
            throw new CreateTransactionException();
        }
    }
}
