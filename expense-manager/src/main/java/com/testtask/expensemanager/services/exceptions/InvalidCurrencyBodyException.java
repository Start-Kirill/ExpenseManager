package com.testtask.expensemanager.services.exceptions;

import com.testtask.expensemanager.core.exceptions.common.StructuredErrorException;

import java.util.Map;

public class InvalidCurrencyBodyException extends StructuredErrorException {
    public InvalidCurrencyBodyException(Map<String, String> errors) {
        super(errors);
    }

    public InvalidCurrencyBodyException(String message, Map<String, String> errors) {
        super(message, errors);
    }

    public InvalidCurrencyBodyException(String message, Throwable cause, Map<String, String> errors) {
        super(message, cause, errors);
    }

    public InvalidCurrencyBodyException(Throwable cause, Map<String, String> errors) {
        super(cause, errors);
    }

    public InvalidCurrencyBodyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Map<String, String> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
