package org.example.transaction.service;

import org.example.transaction.dto.TransactionRequestDTO;
import org.example.transaction.model.Transaction;
public interface TransactionService {
    Transaction createTransaction(TransactionRequestDTO transactionDTO);
}
