package com.testtask.expensemanager.core.converters.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.FailedCovertBigDecimalException;

import java.math.BigDecimal;
import java.util.List;

public class StringToBigDecimalConverter extends StdConverter<String, BigDecimal> {

    private static final String RATE_CONTAINS_INVALID_DATA_MESSAGE = "Exchange rate value contains invalid data";

    @Override
    public BigDecimal convert(String s) {
        try {
            return new BigDecimal(s);
        } catch (NumberFormatException ex) {
            throw new FailedCovertBigDecimalException(RATE_CONTAINS_INVALID_DATA_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, RATE_CONTAINS_INVALID_DATA_MESSAGE)));
        }
    }
}
