package com.testtask.expensemanager.core.exceptions;

import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.common.CommonInternalErrorException;

import java.util.List;

public class FailedConvertDateException extends CommonInternalErrorException {
    public FailedConvertDateException(List<ErrorResponse> errors) {
        super(errors);
    }

    public FailedConvertDateException(String message, List<ErrorResponse> errors) {
        super(message, errors);
    }

    public FailedConvertDateException(String message, Throwable cause, List<ErrorResponse> errors) {
        super(message, cause, errors);
    }

    public FailedConvertDateException(Throwable cause, List<ErrorResponse> errors) {
        super(cause, errors);
    }

    public FailedConvertDateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorResponse> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
