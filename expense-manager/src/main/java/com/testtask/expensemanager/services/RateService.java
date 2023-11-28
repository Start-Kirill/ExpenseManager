package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.dao.api.IRateDao;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.IRateService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RateService implements IRateService {

    private final IRateDao rateDao;

    private final ConversionService conversionService;

    private final ICurrencyService currencyService;

    public RateService(IRateDao rateDao,
                       ConversionService conversionService,
                       ICurrencyService currencyService) {
        this.rateDao = rateDao;
        this.conversionService = conversionService;
        this.currencyService = currencyService;
    }

    @Override
    public Rate get(UUID uuid) {
        try {
            return this.rateDao.findById(uuid).orElseThrow();
//            TODO
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Rate> get() {
        return this.rateDao.findAll();
    }

    @Override
    public Rate save(RateCreateDto rateCreateDto) {

        Rate rate = this.conversionService.convert(rateCreateDto, Rate.class);

        rate.setUuid(UUID.randomUUID());
        rate.setFirstCurrency(this.currencyService.get(rateCreateDto.getFirstCurrencyUuid()));
        rate.setSecondCurrency(this.currencyService.get(rateCreateDto.getSecondCurrencyUuid()));

        try {
            return this.rateDao.save(rate);
//            TODO
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }
}
