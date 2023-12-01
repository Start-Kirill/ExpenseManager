package com.testtask.expensemanager.services.support.spring.converters;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.dao.entyties.Rate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class RateCreateDtoToRateConverterTest {

    @InjectMocks
    private RateCreateDtoToRateConverter converter;

    @Test
    public void shouldConvert() {
        BigDecimal bigDecimal = mock(BigDecimal.class);
        LocalDateTime localDateTime = mock(LocalDateTime.class);

        RateCreateDto rateCreateDto = new RateCreateDto();
        rateCreateDto.setValue(bigDecimal);
        rateCreateDto.setDateTime(localDateTime);

        Rate rate = new Rate();
        rate.setValue(bigDecimal);
        rate.setDate(localDateTime);

        Rate actual = this.converter.convert(rateCreateDto);

        assertNotNull(actual);
        assertEquals(actual, rate);
    }
}
