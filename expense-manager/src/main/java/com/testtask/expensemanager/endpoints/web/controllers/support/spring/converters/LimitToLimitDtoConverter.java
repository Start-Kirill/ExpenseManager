package com.testtask.expensemanager.endpoints.web.controllers.support.spring.converters;

import com.testtask.expensemanager.core.dtos.LimitDto;
import com.testtask.expensemanager.dao.entyties.Limit;
import org.springframework.core.convert.converter.Converter;

public class LimitToLimitDtoConverter implements Converter<Limit, LimitDto> {

    @Override
    public LimitDto convert(Limit source) {
        LimitDto limitDto = new LimitDto();

        limitDto.setUuid(source.getUuid());
        limitDto.setLimitSum(source.getLimitSum());
        limitDto.setExpenseCategory(source.getExpenseCategory());
        limitDto.setDateTime(source.getDateTime());
        limitDto.setCurrencyName(source.getCurrency().getName());

        return limitDto;
    }
}
