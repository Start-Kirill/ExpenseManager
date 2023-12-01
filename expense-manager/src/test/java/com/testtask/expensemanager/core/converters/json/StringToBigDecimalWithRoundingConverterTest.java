package com.testtask.expensemanager.core.converters.json;

import com.testtask.expensemanager.core.exceptions.FailedCovertBigDecimalException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StringToBigDecimalWithRoundingConverterTest {

    @InjectMocks
    private StringToBigDecimalWithRoundingConverter converter;

    @Test
    public void shouldConvert() {
        BigDecimal actual = this.converter.convert("100.014");

        assertNotNull(actual);
        assertEquals(actual, BigDecimal.valueOf(100.01));
    }

    @Test
    public void shouldThrowWhileConvert() {
        assertThrows(FailedCovertBigDecimalException.class, () -> this.converter.convert("asd"));
    }


}
