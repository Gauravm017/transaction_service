package org.example.transaction.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CommonValidation {
    private DocumentNumberValidation documentNumberValidation;

    public static CommonValidation instance;

    private CommonValidation(){
        documentNumberValidation = new DocumentNumberValidation();
    };

    public static CommonValidation getInstance() {
        instance = new CommonValidation();
        return instance;
    }
}
