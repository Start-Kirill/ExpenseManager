package com.testtask.expensemanager.core.converters.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.FailedCovertBigDecimalException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class StringToBigDecimalWithRoundingConverter extends StdConverter<String, BigDecimal> {

    private static final String CONTAINS_INVALID_DATA_MESSAGE = "Value contains invalid data";

    @Override
    public BigDecimal convert(String s) {
        try {
            return new BigDecimal(s).setScale(2, RoundingMode.HALF_UP);
        } catch (NumberFormatException ex) {
            throw new FailedCovertBigDecimalException(CONTAINS_INVALID_DATA_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, CONTAINS_INVALID_DATA_MESSAGE)));
        }
    }
}
