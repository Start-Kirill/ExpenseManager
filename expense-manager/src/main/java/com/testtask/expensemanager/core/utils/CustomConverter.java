package com.testtask.expensemanager.core.utils;

import com.testtask.expensemanager.core.dtos.ExternalRateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateValueDto;
import com.testtask.expensemanager.core.dtos.RateCreateDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomConverter {

    public static List<RateCreateDto> convert(Map<String, ExternalRateDto> externalRates) {
        List<RateCreateDto> rateCreateDtos = new ArrayList<>();

        for (Map.Entry<String, ExternalRateDto> entry : externalRates.entrySet()) {

            List<ExternalRateValueDto> externalRateValueDtos = entry.getValue().getValues();

            if (externalRateValueDtos != null) {

                String[] currencyNames = entry.getKey().split("/");
                String firstCurrencyName = currencyNames[0];
                String secondCurrencyName = currencyNames[1];
                externalRateValueDtos.forEach(ex -> rateCreateDtos.add(new RateCreateDto(firstCurrencyName, secondCurrencyName, ex.getValue(), ex.getDatetime())));

            }

        }

        return rateCreateDtos;
    }
}
