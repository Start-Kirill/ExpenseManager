package com.testtask.expensemanager.endpoints.web.support.spring.converters;

import com.testtask.expensemanager.core.dtos.CurrencyDto;
import com.testtask.expensemanager.dao.entyties.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CurrencyToCurrencyDtoConverterTest {

    @InjectMocks
    private CurrencyToCurrencyDtoConverter converter;

    @Test
    public void shouldConvert() {
        UUID uuid = UUID.randomUUID();
        String name = "USD";

        Currency currency = new Currency(uuid, name);

        CurrencyDto currencyDto = new CurrencyDto(uuid, name);

        CurrencyDto actual = this.converter.convert(currency);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual, currencyDto);
    }
}
