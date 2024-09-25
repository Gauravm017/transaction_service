package org.example.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AccountRequestDTO {

    @Schema(description = "Document number of the account holder", example = "12345678900")
    private String documentNumber;
}
