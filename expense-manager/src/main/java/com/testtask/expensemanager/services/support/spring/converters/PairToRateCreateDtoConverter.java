package com.testtask.expensemanager.services.support.spring.converters;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.util.Pair;

public class PairToRateCreateDtoConverter implements Converter<Pair<String, String>, RateCreateDto> {

    @Override
    public RateCreateDto convert(Pair<String, String> source) {
        RateCreateDto rateCreateDto = new RateCreateDto();

        rateCreateDto.setFirstCurrencyName(source.getFirst());
        rateCreateDto.setSecondCurrencyName(source.getSecond());

        return rateCreateDto;
    }
}
