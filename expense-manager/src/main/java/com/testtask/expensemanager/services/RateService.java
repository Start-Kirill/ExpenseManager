package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.dao.api.IRateDao;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.services.api.IRateService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService implements IRateService {

    private final IRateDao rateDao;

    private final ConversionService conversionService;

    public RateService(IRateDao rateDao,
                       ConversionService conversionService) {
        this.rateDao = rateDao;
        this.conversionService = conversionService;
    }

    @Override
    public List<Rate> get() {
        return null;
    }

    @Override
    public Rate save(RateCreateDto rateCreateDto) {
        return null;
    }
}
