package com.testtask.expensemanager.core.converters.json;

import com.fasterxml.jackson.databind.util.StdConverter;
import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.exceptions.FailedConvertDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class StringToLocalDateTimeConverter extends StdConverter<String, LocalDateTime> {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd";

    private static final String DATE_CONTAINS_INVALID_DATA_MESSAGE = "Date value contains invalid data";

    @Override
    public LocalDateTime convert(String s) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

        try {
            return LocalDateTime.of(LocalDate.parse(s, formatter), LocalTime.MIN);
        } catch (DateTimeParseException ex) {
            throw new FailedConvertDateException(DATE_CONTAINS_INVALID_DATA_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, DATE_CONTAINS_INVALID_DATA_MESSAGE)));
        }
    }
}
