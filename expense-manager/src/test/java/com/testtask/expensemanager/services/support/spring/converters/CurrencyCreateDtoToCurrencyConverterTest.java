package com.testtask.expensemanager.services.support.spring.converters;

import com.testtask.expensemanager.core.dtos.CurrencyCreateDto;
import com.testtask.expensemanager.dao.entyties.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CurrencyCreateDtoToCurrencyConverterTest {

    @InjectMocks
    private CurrencyCreateDtoToCurrencyConverter converter;

    @Test
    public void shouldConvert() {
        Currency currency = new Currency();
        currency.setName("USD");

        CurrencyCreateDto currencyCreateDto = new CurrencyCreateDto("USD");

        Currency actual = this.converter.convert(currencyCreateDto);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual, currency);
    }
}
