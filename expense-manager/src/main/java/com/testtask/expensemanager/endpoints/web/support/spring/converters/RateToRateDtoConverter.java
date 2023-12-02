package com.testtask.expensemanager.endpoints.web.support.spring.converters;

import com.testtask.expensemanager.core.dtos.RateDto;
import com.testtask.expensemanager.dao.entyties.Rate;
import org.springframework.core.convert.converter.Converter;

public class RateToRateDtoConverter implements Converter<Rate, RateDto> {
    @Override
    public RateDto convert(Rate source) {

        RateDto rateDto = new RateDto();

        rateDto.setUuid(source.getUuid());
        rateDto.setValue(source.getValue());
        rateDto.setFirstCurrencyName(source.getFirstCurrency().getName());
        rateDto.setSecondCurrencyName(source.getSecondCurrency().getName());
        rateDto.setValue(source.getValue());
        rateDto.setDateTime(source.getDatetime());
        rateDto.setStatus(source.getStatus());
        rateDto.setDtCreate(source.getDtCreate());
        rateDto.setDtUpdate(source.getDtUpdate());


        return rateDto;
    }
}
