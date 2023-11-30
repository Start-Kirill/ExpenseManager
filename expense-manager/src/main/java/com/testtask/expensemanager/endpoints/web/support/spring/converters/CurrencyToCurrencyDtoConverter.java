package com.testtask.expensemanager.endpoints.web.support.spring.converters;

import com.testtask.expensemanager.core.dtos.CurrencyDto;
import com.testtask.expensemanager.dao.entyties.Currency;
import org.springframework.core.convert.converter.Converter;

public class CurrencyToCurrencyDtoConverter implements Converter<Currency, CurrencyDto> {
    @Override
    public CurrencyDto convert(Currency source) {
        CurrencyDto currencyDto = new CurrencyDto();

        currencyDto.setUuid(source.getUuid());
        currencyDto.setName(source.getName());

        return currencyDto;
    }
}
