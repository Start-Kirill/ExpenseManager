package com.testtask.expensemanager.services.exceptions;

import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.common.CommonInternalErrorException;

import java.util.List;

public class FailedRateAccessException extends CommonInternalErrorException {
    public FailedRateAccessException(List<ErrorResponse> errors) {
        super(errors);
    }

    public FailedRateAccessException(String message, List<ErrorResponse> errors) {
        super(message, errors);
    }

    public FailedRateAccessException(String message, Throwable cause, List<ErrorResponse> errors) {
        super(message, cause, errors);
    }

    public FailedRateAccessException(Throwable cause, List<ErrorResponse> errors) {
        super(cause, errors);
    }

    public FailedRateAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorResponse> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
