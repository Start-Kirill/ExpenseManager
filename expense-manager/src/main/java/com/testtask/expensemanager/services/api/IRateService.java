package com.testtask.expensemanager.services.api;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.core.enums.RateStatus;
import com.testtask.expensemanager.dao.entyties.Rate;

import java.util.List;

public interface IRateService extends ICRUDService<Rate, RateCreateDto> {

    boolean isEmpty();

    Rate update(Rate rate);

    List<Rate> updateAll(List<Rate> rates);

    Rate getFirstUpToDate();

    Rate getFirstUpToDate(String firstCurrencyName, String secondCurrencyName);

    List<Rate> saveAll(List<RateCreateDto> rateCreateDto);

    List<Rate> getByStatus(RateStatus status);


}
