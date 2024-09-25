package org.example.service;

import org.example.transaction.dto.TransactionRequestDTO;
import org.example.transaction.exception.AccountNotFoundException;
import org.example.transaction.exception.InvalidOperationTypeException;
import org.example.transaction.model.Account;
import org.example.transaction.model.OperationType;
import org.example.transaction.model.Transaction;
import org.example.transaction.repository.AccountRepository;
import org.example.transaction.repository.OperationTypeRepository;
import org.example.transaction.repository.TransactionRepository;
import org.example.transaction.service.TransactionServiceImpl;
import org.example.transaction.validation.TransactionRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private OperationTypeRepository operationTypeRepository;

    @Mock
    private TransactionRequestValidator transactionRequestValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTransactionTest() {
        Account account = new Account();
        account.setAccountId(1L);

        OperationType operationType = new OperationType();
        operationType.setOperationTypeId(3L);

        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setAccount(account);
        transaction.setOperationType(operationType);
        transaction.setAmount(57.7);

        TransactionRequestDTO transactionDTO = new TransactionRequestDTO();
        transactionDTO.setAccountId(1L);
        transactionDTO.setOperationTypeId(3L);
        transactionDTO.setAmount(57.7);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(operationTypeRepository.findById(3L)).thenReturn(Optional.of(operationType));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        Transaction createdTransaction = transactionService.createTransaction(transactionDTO);
        assertEquals(1L, createdTransaction.getTransactionId());
        assertEquals(57.7, createdTransaction.getAmount());
    }

    @Test
    public void createTransactionAccountNotFoundTest() {
        TransactionRequestDTO transactionDTO = new TransactionRequestDTO();
        transactionDTO.setAccountId(1L);
        transactionDTO.setOperationTypeId(3L);
        transactionDTO.setAmount(100.1);

        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> transactionService.createTransaction(transactionDTO));
    }

    @Test
    public void createTransactionInvalidOperationTypeTest() {
        Account account = new Account();
        account.setAccountId(1L);

        TransactionRequestDTO transactionDTO = new TransactionRequestDTO();
        transactionDTO.setAccountId(1L);
        transactionDTO.setOperationTypeId(5L);
        transactionDTO.setAmount(111.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(operationTypeRepository.findById(5L)).thenReturn(Optional.empty());

        assertThrows(InvalidOperationTypeException.class, ()-> transactionService.createTransaction(transactionDTO));
    }
}
