package com.testtask.expensemanager.services.support.spring.converters;

import com.testtask.expensemanager.core.dtos.ExternalRateCreateDto;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Rate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatesListToExternalRateCreateDtoTest {

    @InjectMocks
    private RatesListToExternalRateCreateDto converter;

    @Test
    public void shouldConvert() {
        List<Rate> rates = new ArrayList<>();
        Rate rate = mock(Rate.class);
        rates.add(rate);
        Currency currency = mock(Currency.class);
        String name = "name";
        when(currency.getName()).thenReturn(name);
        when(rate.getFirstCurrency()).thenReturn(currency);
        when(rate.getSecondCurrency()).thenReturn(currency);
        LocalDateTime now = LocalDateTime.now();
        when(rate.getDatetime()).thenReturn(now);

        ExternalRateCreateDto actual = this.converter.convert(rates);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual, new ExternalRateCreateDto(List.of(Pair.of(name, name)), now.toLocalDate(), now.plusDays(1).toLocalDate()));
    }
}
