package org.example.transaction.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseCriteria implements BaseValidation {

    protected boolean isRequired;

    private String validationExpression;

    @Override
    public boolean validate(Object data) {
        if (isRequired) {
            if (data == null)
                return false;
            String field = String.valueOf(data);
            if (StringUtils.isBlank(field)) {
                return false;
            }
            if (StringUtils.isNoneBlank(validationExpression)
                    && !Pattern.compile(validationExpression).matcher(field).matches()) {
                return false;
            }
        }
        return true;
    }
}
