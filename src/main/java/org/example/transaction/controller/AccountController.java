package org.example.transaction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.transaction.dto.AccountRequestDTO;
import org.example.transaction.model.Account;
import org.example.transaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Operation(summary = "Create a new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created",content = @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Document number to create the account") AccountRequestDTO accountDTO) {
        Account account = accountService.createAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @Operation(summary = "Retrieve account by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found",content = @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@Parameter(description = "ID of the account to retrieve") @PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.ok(account);
    }
}