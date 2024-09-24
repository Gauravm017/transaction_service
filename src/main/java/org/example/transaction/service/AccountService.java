package org.example.transaction.service;

import org.example.transaction.dto.AccountRequestDTO;
import org.example.transaction.model.Account;
public interface AccountService {
    Account createAccount(AccountRequestDTO accountDTO);

    Account getAccountById(Long accountId);
}