package com.testtask.expensemanager.core.converters.json;

import com.testtask.expensemanager.core.exceptions.FailedConvertDateException;
import com.testtask.expensemanager.core.exceptions.FailedCovertBigDecimalException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StringToLocalDateTimeConverterTest {

    @InjectMocks
    private StringToLocalDateTimeConverter converter;

    @Test
    public void shouldConvert() {
        LocalDateTime dateTime = this.converter.convert("2023-12-01");

        assertNotNull(dateTime);
        assertEquals(dateTime, LocalDateTime.of(LocalDate.of(2023, 12, 1), LocalTime.MIN));
    }

    @Test
    public void shouldThrowWhileConvert() {
        assertThrows(FailedConvertDateException.class, () -> this.converter.convert("asd"));
    }

}
