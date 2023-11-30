package com.testtask.expensemanager.core.converters.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.FailedCovertBigDecimalException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class StringToBigDecimalConverter extends StdConverter<String, BigDecimal> {
    @Override
    public BigDecimal convert(String s) {
        try {
            return new BigDecimal(s);
        } catch (NumberFormatException ex) {
            throw new FailedCovertBigDecimalException(List.of(new ErrorResponse(ErrorType.ERROR, "Exchange rate value contains invalid data")));
        }
    }
}
