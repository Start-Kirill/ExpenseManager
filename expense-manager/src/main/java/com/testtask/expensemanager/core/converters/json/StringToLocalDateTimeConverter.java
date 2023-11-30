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

    @Override
    public LocalDateTime convert(String s) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

        try {
            return LocalDateTime.of(LocalDate.parse(s, formatter), LocalTime.MIN);
        } catch (DateTimeParseException ex) {
            throw new FailedConvertDateException(List.of(new ErrorResponse(ErrorType.ERROR, "Exchange rate date contains invalid data")));
        }
    }
}
