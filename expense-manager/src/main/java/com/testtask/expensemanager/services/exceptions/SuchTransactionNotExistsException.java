package com.testtask.expensemanager.services.exceptions;

import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.common.CommonInternalErrorException;

import java.util.List;

public class SuchTransactionNotExistsException extends CommonInternalErrorException {
    public SuchTransactionNotExistsException(List<ErrorResponse> errors) {
        super(errors);
    }

    public SuchTransactionNotExistsException(String message, List<ErrorResponse> errors) {
        super(message, errors);
    }

    public SuchTransactionNotExistsException(String message, Throwable cause, List<ErrorResponse> errors) {
        super(message, cause, errors);
    }

    public SuchTransactionNotExistsException(Throwable cause, List<ErrorResponse> errors) {
        super(cause, errors);
    }

    public SuchTransactionNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorResponse> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
