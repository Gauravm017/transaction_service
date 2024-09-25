package org.example.transaction.validation;

import org.example.transaction.Utils.FlowType;
import org.example.transaction.dto.AccountRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class AccountRequestValidator extends BaseValidator{

    @Override
    public boolean handle(final String flowType) {
        return FlowType.ACCOUNT.name().equalsIgnoreCase(flowType);
    }

    @Override
    public void validateAccountAdditionRequest(AccountRequestDTO accountRequestDTO){
        commonValidation.getDocumentNumberValidation().validate(accountRequestDTO.getDocumentNumber());
    }

}
