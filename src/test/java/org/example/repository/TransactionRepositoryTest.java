package org.example.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.transaction.model.Account;
import org.example.transaction.model.OperationType;
import org.example.transaction.model.Transaction;
import org.example.transaction.repository.AccountRepository;
import org.example.transaction.repository.OperationTypeRepository;
import org.example.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.assertj.core.api.Assertions;

import java.time.LocalDateTime;

@DataJpaTest
@Slf4j
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    OperationTypeRepository operationTypeRepository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("Test 1: Create Transaction Test")
    @Order(1)
    @Rollback(value = false)
    public void createTransactionTest(){
        Account account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber("6543234566");

        accountRepository.save(account);

        OperationType operationType = new OperationType();
        operationType.setOperationTypeId(1L);
        operationType.setDescription("Withdrawal");

        operationTypeRepository.save(operationType);

        Transaction transaction = new Transaction();
        transaction.setAmount(189.00);
        transaction.setAccount(account);
        transaction.setOperationType(operationType);
        transaction.setEventDate(LocalDateTime.now());

        transactionRepository.save(transaction);

        Assertions.assertThat(transaction.getTransactionId()).isGreaterThan(0);

    }

}
