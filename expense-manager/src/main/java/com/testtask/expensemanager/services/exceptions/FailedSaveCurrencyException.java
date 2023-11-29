package com.testtask.expensemanager.services.exceptions;

import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.CommonInternalErrorException;

import java.util.List;

public class FailedSaveCurrencyException extends CommonInternalErrorException {
    public FailedSaveCurrencyException(List<ErrorResponse> errors) {
        super(errors);
    }

    public FailedSaveCurrencyException(String message, List<ErrorResponse> errors) {
        super(message, errors);
    }

    public FailedSaveCurrencyException(String message, Throwable cause, List<ErrorResponse> errors) {
        super(message, cause, errors);
    }

    public FailedSaveCurrencyException(Throwable cause, List<ErrorResponse> errors) {
        super(cause, errors);
    }

    public FailedSaveCurrencyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorResponse> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
