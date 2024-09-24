package org.example.transaction.validation;

import org.reactivestreams.Publisher;

public interface BaseValidation {

    public boolean validate(Object data);

}
