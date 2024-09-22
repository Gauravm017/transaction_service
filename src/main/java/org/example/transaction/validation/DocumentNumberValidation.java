package org.example.transaction.validation;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.transaction.exception.InvalidDocumentNumberException;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class DocumentNumberValidation extends BaseEntityDecorator{

    public static String DOCUMENT_NUMBER_PATTERN = "^[0-9]*$";

    public DocumentNumberValidation(){
        setCriteria(new BaseCriteria());
    }

    @Override
    public boolean validate(Object docNo) {

        if (ObjectUtils.isEmpty(docNo)) {
            log.error("Ifsc basic validation failed, ifsc is either blank or invalid");
            throw new InvalidDocumentNumberException("INVALID DOCUMENT NUMBER");
        }

         if(StringUtils.isBlank(getCriteria().getValidationExpression())) {
             getCriteria().setRequired(false);
             getCriteria().setValidationExpression(DOCUMENT_NUMBER_PATTERN);
         }
         if(!super.validate(docNo)){
             log.error("Document Number basic validation failed, Document Number is either blank or invalid {}", docNo);
             throw new InvalidDocumentNumberException("INVALID DOCUMENT NUMBER");
         }
        return true;
    }

}
