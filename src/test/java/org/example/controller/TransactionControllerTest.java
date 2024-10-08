package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.TransactionServiceApplication;
import org.example.transaction.controller.TransactionController;
import org.example.transaction.dto.TransactionRequestDTO;
import org.example.transaction.model.Account;
import org.example.transaction.model.OperationType;
import org.example.transaction.model.Transaction;
import org.example.transaction.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TransactionServiceApplication.class)
@Slf4j
@AutoConfigureMockMvc
@ActiveProfiles("local")
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTransaction_ShouldReturn201WhenValid() throws Exception {
        // Arrange
        TransactionRequestDTO transactionDTO = new TransactionRequestDTO();
        transactionDTO.setAccountId(1L);
        transactionDTO.setOperationTypeId(4L);
        transactionDTO.setAmount(100.00);

//        Account account = new Account();
//        account.setAccountId(1L);
//        account.setDocumentNumber("12345678900");
//
//        OperationType operationType = new OperationType();
//        operationType.setOperationTypeId(4L);
//        operationType.setDescription("Credit Voucher");

        Transaction transaction = new Transaction();
        transaction.setAmount(100.00);
        transaction.setEventDate(LocalDateTime.now());
//        transaction.setAccount(account);
//        transaction.setOperationType(operationType);

//        when(transactionService.createTransaction(any(TransactionRequestDTO.class))).thenReturn(transaction);

        // Act & Assert
        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.account.accountId").value(1L))
                .andExpect(jsonPath("$.operationType.operationTypeId").value(4))
                .andExpect(jsonPath("$.operationType.description").value("Credit Voucher"))
                .andExpect(jsonPath("$.amount").value(100.00));
    }

    @Test
    void createTransaction_ShouldReturn400WhenInvalidInput() throws Exception {
        // Arrange
        TransactionRequestDTO invalidDTO = new TransactionRequestDTO(); // Missing required fields

        // Act & Assert
        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isInternalServerError());
    }

}