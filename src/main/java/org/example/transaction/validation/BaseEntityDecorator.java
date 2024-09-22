package org.example.transaction.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseEntityDecorator implements BaseValidation{

    BaseCriteria criteria;

    @Override
    public boolean validate(Object field) {
        return criteria.validate(field);
    }

}
