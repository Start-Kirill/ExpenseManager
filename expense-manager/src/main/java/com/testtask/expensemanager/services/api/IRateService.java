package com.testtask.expensemanager.services.api;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.dao.entyties.Rate;

import java.time.LocalDateTime;
import java.util.List;

public interface IRateService extends ICRUDService<Rate, RateCreateDto> {

    Rate getFirstUpToDate();

    Rate getFirstUpToDate(String firstCurrencyName, String secondCurrencyName);

    List<Rate> saveAll(List<RateCreateDto> rateCreateDto);


}
