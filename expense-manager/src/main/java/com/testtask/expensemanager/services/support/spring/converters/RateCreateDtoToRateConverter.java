package com.testtask.expensemanager.services.support.spring.converters;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.dao.entyties.Rate;
import org.springframework.core.convert.converter.Converter;

public class RateCreateDtoToRateConverter implements Converter<RateCreateDto, Rate> {
    @Override
    public Rate convert(RateCreateDto source) {

        Rate rate = new Rate();

        rate.setValue(source.getValue());
        rate.setDate(source.getDateTime());

        return rate;
    }
}
