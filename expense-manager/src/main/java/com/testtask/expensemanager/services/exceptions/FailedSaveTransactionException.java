package com.testtask.expensemanager.services.exceptions;

import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.CommonInternalErrorException;

import java.util.List;

public class FailedSaveTransactionException extends CommonInternalErrorException {
    public FailedSaveTransactionException(List<ErrorResponse> errors) {
        super(errors);
    }

    public FailedSaveTransactionException(String message, List<ErrorResponse> errors) {
        super(message, errors);
    }

    public FailedSaveTransactionException(String message, Throwable cause, List<ErrorResponse> errors) {
        super(message, cause, errors);
    }

    public FailedSaveTransactionException(Throwable cause, List<ErrorResponse> errors) {
        super(cause, errors);
    }

    public FailedSaveTransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorResponse> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
