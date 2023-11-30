package com.testtask.expensemanager.services.exceptions;

import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.common.CommonInternalErrorException;

import java.util.List;

public class SuchLimitNotExistsException extends CommonInternalErrorException {
    public SuchLimitNotExistsException(List<ErrorResponse> errors) {
        super(errors);
    }

    public SuchLimitNotExistsException(String message, List<ErrorResponse> errors) {
        super(message, errors);
    }

    public SuchLimitNotExistsException(String message, Throwable cause, List<ErrorResponse> errors) {
        super(message, cause, errors);
    }

    public SuchLimitNotExistsException(Throwable cause, List<ErrorResponse> errors) {
        super(cause, errors);
    }

    public SuchLimitNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorResponse> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
