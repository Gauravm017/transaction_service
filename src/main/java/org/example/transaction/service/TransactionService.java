package org.example.transaction.service;

import org.example.transaction.dto.TransactionRequestDTO;
import org.example.transaction.exception.AccountNotFoundException;
import org.example.transaction.exception.InvalidOperationTypeException;
import org.example.transaction.model.Account;
import org.example.transaction.model.OperationType;
import org.example.transaction.model.Transaction;
import org.example.transaction.repository.AccountRepository;
import org.example.transaction.repository.OperationTypeRepository;
import org.example.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationTypeRepository operationTypeRepository;

    public Transaction createTransaction(TransactionRequestDTO transactionDTO) {
        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        OperationType operationType = operationTypeRepository.findById(transactionDTO.getOperationTypeId())
                .orElseThrow(() -> new InvalidOperationTypeException("Invalid operation type"));

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setOperationType(operationType);
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setEventDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
}
