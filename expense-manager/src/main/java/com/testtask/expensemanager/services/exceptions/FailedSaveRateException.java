package com.testtask.expensemanager.services.exceptions;

import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.CommonInternalErrorException;

import java.util.List;

public class FailedSaveRateException extends CommonInternalErrorException {
    public FailedSaveRateException(List<ErrorResponse> errors) {
        super(errors);
    }

    public FailedSaveRateException(String message, List<ErrorResponse> errors) {
        super(message, errors);
    }

    public FailedSaveRateException(String message, Throwable cause, List<ErrorResponse> errors) {
        super(message, cause, errors);
    }

    public FailedSaveRateException(Throwable cause, List<ErrorResponse> errors) {
        super(cause, errors);
    }

    public FailedSaveRateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorResponse> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
