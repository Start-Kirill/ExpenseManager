package com.testtask.expensemanager.services.support.spring.converters;

import com.testtask.expensemanager.core.dtos.LimitCreateDto;
import com.testtask.expensemanager.dao.entyties.Limit;
import org.springframework.core.convert.converter.Converter;

public class LimitCreateDtoToLimitConverter implements Converter<LimitCreateDto, Limit> {
    @Override
    public Limit convert(LimitCreateDto source) {
        Limit limit = new Limit();

        limit.setLimitSum(source.getLimitSum());
        limit.setExpenseCategory(source.getExpenseCategory());

        return limit;
    }
}
