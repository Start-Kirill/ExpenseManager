package com.testtask.expensemanager.services.exceptions;

import com.testtask.expensemanager.core.exceptions.StructuredErrorException;

import java.util.Map;

public class InvalidTransactionBodyException extends StructuredErrorException {
    public InvalidTransactionBodyException(Map<String, String> errors) {
        super(errors);
    }

    public InvalidTransactionBodyException(String message, Map<String, String> errors) {
        super(message, errors);
    }

    public InvalidTransactionBodyException(String message, Throwable cause, Map<String, String> errors) {
        super(message, cause, errors);
    }

    public InvalidTransactionBodyException(Throwable cause, Map<String, String> errors) {
        super(cause, errors);
    }

    public InvalidTransactionBodyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Map<String, String> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
