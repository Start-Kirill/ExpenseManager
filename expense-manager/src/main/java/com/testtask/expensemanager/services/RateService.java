package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.dao.api.IRateDao;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.services.api.IRateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService implements IRateService {

    private final IRateDao rateDao;

    public RateService(IRateDao rateDao) {
        this.rateDao = rateDao;
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
