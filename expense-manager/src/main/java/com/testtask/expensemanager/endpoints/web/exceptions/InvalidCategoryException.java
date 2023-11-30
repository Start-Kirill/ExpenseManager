package com.testtask.expensemanager.endpoints.web.exceptions;

import com.testtask.expensemanager.core.exceptions.common.StructuredErrorException;

import java.util.Map;

public class InvalidCategoryException extends StructuredErrorException {
    public InvalidCategoryException(Map<String, String> errors) {
        super(errors);
    }

    public InvalidCategoryException(String message, Map<String, String> errors) {
        super(message, errors);
    }

    public InvalidCategoryException(String message, Throwable cause, Map<String, String> errors) {
        super(message, cause, errors);
    }

    public InvalidCategoryException(Throwable cause, Map<String, String> errors) {
        super(cause, errors);
    }

    public InvalidCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Map<String, String> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
