package com.testtask.expensemanager.core.exceptions;

import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.common.CommonInternalErrorException;

import java.util.List;

public class FailedCovertBigDecimalException extends CommonInternalErrorException {
    public FailedCovertBigDecimalException(List<ErrorResponse> errors) {
        super(errors);
    }

    public FailedCovertBigDecimalException(String message, List<ErrorResponse> errors) {
        super(message, errors);
    }

    public FailedCovertBigDecimalException(String message, Throwable cause, List<ErrorResponse> errors) {
        super(message, cause, errors);
    }

    public FailedCovertBigDecimalException(Throwable cause, List<ErrorResponse> errors) {
        super(cause, errors);
    }

    public FailedCovertBigDecimalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorResponse> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
