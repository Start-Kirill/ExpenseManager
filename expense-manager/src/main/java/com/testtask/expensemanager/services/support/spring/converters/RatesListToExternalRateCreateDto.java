package com.testtask.expensemanager.services.support.spring.converters;


import com.testtask.expensemanager.core.dtos.ExternalRateCreateDto;
import com.testtask.expensemanager.dao.entyties.Rate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RatesListToExternalRateCreateDto implements Converter<List<Rate>, ExternalRateCreateDto> {
    @Override
    public ExternalRateCreateDto convert(List<Rate> source) {
        List<Pair<String, String>> pairs = new ArrayList<>();
        LocalDate startDate = null;
        LocalDate endDate = null;
        for (Rate rate : source) {
            pairs.add(Pair.of(rate.getFirstCurrency().getName(), rate.getSecondCurrency().getName()));
            if (startDate == null || startDate.isBefore(rate.getDatetime().toLocalDate())) {
                startDate = rate.getDatetime().toLocalDate();
            }
            if (endDate == null || endDate.isAfter(rate.getDatetime().toLocalDate())) {
                endDate = rate.getDatetime().toLocalDate().plusDays(1);
            }
        }

        return new ExternalRateCreateDto(pairs, startDate, endDate);
    }
}
