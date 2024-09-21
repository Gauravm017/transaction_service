package org.example.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransactionRequestDTO {

    @Schema(description = "ID of the account where the transaction is being performed", example = "1")
    private Long accountId;
    @Schema(description = "ID of the type of operation being performed", example = "4")
    private Long operationTypeId;
    @Schema(description = "The amount involved in the transaction", example = "123.45")
    private Double amount;
}
