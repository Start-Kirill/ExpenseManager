package com.testtask.expensemanager.services.support.spring.converters;

import com.testtask.expensemanager.core.dtos.CurrencyCreateDto;
import com.testtask.expensemanager.dao.entyties.Currency;
import org.springframework.core.convert.converter.Converter;

public class CurrencyCreateDtoToCurrencyConverter implements Converter<CurrencyCreateDto, Currency> {
    @Override
    public Currency convert(CurrencyCreateDto source) {
        Currency currency = new Currency();

        currency.setName(source.getName());

        return currency;
    }
}
