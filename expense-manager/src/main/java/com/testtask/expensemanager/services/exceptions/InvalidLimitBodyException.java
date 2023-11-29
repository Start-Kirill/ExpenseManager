package com.testtask.expensemanager.services.exceptions;

import com.testtask.expensemanager.core.exceptions.StructuredErrorException;

import java.util.Map;

public class InvalidLimitBodyException extends StructuredErrorException {
    public InvalidLimitBodyException(Map<String, String> errors) {
        super(errors);
    }

    public InvalidLimitBodyException(String message, Map<String, String> errors) {
        super(message, errors);
    }

    public InvalidLimitBodyException(String message, Throwable cause, Map<String, String> errors) {
        super(message, cause, errors);
    }

    public InvalidLimitBodyException(Throwable cause, Map<String, String> errors) {
        super(cause, errors);
    }

    public InvalidLimitBodyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Map<String, String> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
