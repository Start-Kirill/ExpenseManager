package com.testtask.expensemanager.services.exceptions;

import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.common.CommonInternalErrorException;

import java.util.List;

public class SuchRateNotExistsException extends CommonInternalErrorException {
    public SuchRateNotExistsException(List<ErrorResponse> errors) {
        super(errors);
    }

    public SuchRateNotExistsException(String message, List<ErrorResponse> errors) {
        super(message, errors);
    }

    public SuchRateNotExistsException(String message, Throwable cause, List<ErrorResponse> errors) {
        super(message, cause, errors);
    }

    public SuchRateNotExistsException(Throwable cause, List<ErrorResponse> errors) {
        super(cause, errors);
    }

    public SuchRateNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorResponse> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
