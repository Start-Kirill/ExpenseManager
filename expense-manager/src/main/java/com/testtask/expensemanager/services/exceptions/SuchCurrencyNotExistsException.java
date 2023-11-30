package com.testtask.expensemanager.services.exceptions;

import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.common.CommonInternalErrorException;

import java.util.List;

public class SuchCurrencyNotExistsException extends CommonInternalErrorException {
    public SuchCurrencyNotExistsException(List<ErrorResponse> errors) {
        super(errors);
    }

    public SuchCurrencyNotExistsException(String message, List<ErrorResponse> errors) {
        super(message, errors);
    }

    public SuchCurrencyNotExistsException(String message, Throwable cause, List<ErrorResponse> errors) {
        super(message, cause, errors);
    }

    public SuchCurrencyNotExistsException(Throwable cause, List<ErrorResponse> errors) {
        super(cause, errors);
    }

    public SuchCurrencyNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorResponse> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
