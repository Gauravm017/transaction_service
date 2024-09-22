package org.example.transaction.service;

import org.example.transaction.Utils.FlowType;
import org.example.transaction.dto.AccountRequestDTO;
import org.example.transaction.exception.AccountNotFoundException;
import org.example.transaction.model.Account;
import org.example.transaction.repository.AccountRepository;
import org.example.transaction.validation.AccountRequestValidator;
import org.example.transaction.validation.BaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRequestValidator accountRequestValidator;


    public Account createAccount(AccountRequestDTO accountDTO) {
        accountRequestValidator.validateAccountAdditionRequest(accountDTO);
        Account account = new Account();
        account.setDocumentNumber(accountDTO.getDocumentNumber());
        return accountRepository.save(account);
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }
}