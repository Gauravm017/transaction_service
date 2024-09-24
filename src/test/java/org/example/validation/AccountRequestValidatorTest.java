package org.example.validation;

import lombok.extern.slf4j.Slf4j;
import org.example.TransactionServiceApplication;
import org.example.transaction.dto.AccountRequestDTO;
import org.example.transaction.exception.AccountNotFoundException;
import org.example.transaction.exception.InvalidDocumentNumberException;
import org.example.transaction.validation.AccountRequestValidator;
import org.example.transaction.validation.CommonValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static reactor.core.publisher.Mono.when;

@ActiveProfiles("test")
@SpringBootTest(classes = TransactionServiceApplication.class)
public class AccountRequestValidatorTest {


    @Autowired
    AccountRequestValidator accountRequestValidator;

    @Test
    public void testAccountRequestValidator_Failure(){
        AccountRequestDTO accountDTO = new AccountRequestDTO();
        accountDTO.setDocumentNumber("aasddsad");

        assertThrows(InvalidDocumentNumberException.class, () -> accountRequestValidator.validateAccountAdditionRequest(accountDTO));
    }

    @Test
    public void testAccountRequestValidator_Success(){
        AccountRequestDTO accountDTO = new AccountRequestDTO();
        accountDTO.setDocumentNumber("98765432100");
        assertDoesNotThrow(() ->accountRequestValidator.validateAccountAdditionRequest(accountDTO));
    }
}
