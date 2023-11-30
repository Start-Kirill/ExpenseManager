package com.testtask.expensemanager.services.exceptions;

import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.common.CommonInternalErrorException;

import java.util.List;

public class FailedSaveLimitException extends CommonInternalErrorException {
    public FailedSaveLimitException(List<ErrorResponse> errors) {
        super(errors);
    }

    public FailedSaveLimitException(String message, List<ErrorResponse> errors) {
        super(message, errors);
    }

    public FailedSaveLimitException(String message, Throwable cause, List<ErrorResponse> errors) {
        super(message, cause, errors);
    }

    public FailedSaveLimitException(Throwable cause, List<ErrorResponse> errors) {
        super(cause, errors);
    }

    public FailedSaveLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorResponse> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
