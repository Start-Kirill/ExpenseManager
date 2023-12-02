package com.testtask.expensemanager.services.exceptions;

import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.common.CommonInternalErrorException;

import java.util.List;

public class RateDateTimeUpdatesNotMatchException extends CommonInternalErrorException {
    public RateDateTimeUpdatesNotMatchException(List<ErrorResponse> errors) {
        super(errors);
    }

    public RateDateTimeUpdatesNotMatchException(String message, List<ErrorResponse> errors) {
        super(message, errors);
    }

    public RateDateTimeUpdatesNotMatchException(String message, Throwable cause, List<ErrorResponse> errors) {
        super(message, cause, errors);
    }

    public RateDateTimeUpdatesNotMatchException(Throwable cause, List<ErrorResponse> errors) {
        super(cause, errors);
    }

    public RateDateTimeUpdatesNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, List<ErrorResponse> errors) {
        super(message, cause, enableSuppression, writableStackTrace, errors);
    }
}
