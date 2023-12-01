package com.testtask.expensemanager.endpoints.web.support.spring.converters;

import com.testtask.expensemanager.core.dtos.LimitDto;
import com.testtask.expensemanager.core.dtos.RateDto;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Limit;
import com.testtask.expensemanager.dao.entyties.Rate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RateToRateDtoConverterTest {

    @InjectMocks
    private RateToRateDtoConverter converter;

    @Test
    public void shouldConvert() {
        UUID uuid = UUID.randomUUID();
        LocalDateTime dateTime = mock(LocalDateTime.class);
        Currency currency = mock(Currency.class);
        when(currency.getName()).thenReturn("USD");
        BigDecimal value = mock(BigDecimal.class);
        Rate rate = new Rate(uuid, currency, currency, value, dateTime);
        RateDto rateDto = new RateDto(uuid, "USD", "USD", value, dateTime);


        RateDto actual = this.converter.convert(rate);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual, rateDto);
    }
}
