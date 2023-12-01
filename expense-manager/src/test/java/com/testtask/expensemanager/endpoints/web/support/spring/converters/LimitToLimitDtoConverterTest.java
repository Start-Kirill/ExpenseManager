package com.testtask.expensemanager.endpoints.web.support.spring.converters;


import com.testtask.expensemanager.core.dtos.CurrencyDto;
import com.testtask.expensemanager.core.dtos.LimitDto;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Limit;
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
public class LimitToLimitDtoConverterTest {

    @InjectMocks
    private LimitToLimitDtoConverter converter;

    @Test
    public void shouldConvert() {
        UUID uuid = UUID.randomUUID();
        LocalDateTime dateTime = mock(LocalDateTime.class);
        Currency currency = mock(Currency.class);
        when(currency.getName()).thenReturn("USD");

        Limit limit = new Limit(uuid, ExpenseCategory.PRODUCT, BigDecimal.valueOf(1000), currency, dateTime);

        LimitDto limitDto = new LimitDto(uuid, dateTime, ExpenseCategory.PRODUCT, BigDecimal.valueOf(1000), "USD");

        LimitDto actual = this.converter.convert(limit);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual, limitDto);
    }
}
