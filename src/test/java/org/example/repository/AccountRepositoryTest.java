package org.example.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.transaction.model.Account;
import org.example.transaction.repository.AccountRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.assertj.core.api.Assertions;
import org.springframework.test.context.TestPropertySource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("Test 1: Account Insert Test")
    @Order(1)
    @Rollback(value = false)
    public void saveAccountTest(){
        Account account = new Account();
        account.setDocumentNumber("12345678900");

        accountRepository.save(account);

        Assertions.assertThat(account.getAccountId()).isGreaterThan(0);
    }
}
