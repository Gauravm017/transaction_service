package org.example.service;

import org.example.transaction.dto.AccountRequestDTO;
import org.example.transaction.exception.AccountNotFoundException;
import org.example.transaction.model.Account;
import org.example.transaction.repository.AccountRepository;
import org.example.transaction.service.AccountService;
import org.example.transaction.service.AccountServiceImpl;
import org.example.transaction.validation.AccountRequestValidator;
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
public class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountRequestValidator accountRequestValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createAccountTest() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber("12345678900");

        AccountRequestDTO accountDTO = new AccountRequestDTO();
        accountDTO.setDocumentNumber("12345678900");

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.createAccount(accountDTO);
        assertEquals(1L, createdAccount.getAccountId());
        assertEquals("12345678900", createdAccount.getDocumentNumber());
    }

    @Test
    public void getAccountByIdTest() {
        Account account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber("12345678900");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        Account foundAccount = accountService.getAccountById(1L);
        assertEquals(1L, foundAccount.getAccountId());
        assertEquals("12345678900", foundAccount.getDocumentNumber());
    }

    @Test
    public void getAccountByIdNotFoundTest() {
        when(accountRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(1L));
    }
}
