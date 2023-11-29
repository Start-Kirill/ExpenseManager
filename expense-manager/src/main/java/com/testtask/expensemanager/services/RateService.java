package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.dao.api.IRateDao;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.IRateService;
import com.testtask.expensemanager.services.exceptions.FailedSaveRateException;
import com.testtask.expensemanager.services.exceptions.SuchRateNotExistsException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        } catch (NoSuchElementException ex) {
            throw new SuchRateNotExistsException(List.of(new ErrorResponse(ErrorType.ERROR, "There is no such rate in database")));
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
        } catch (Exception ex) {
            throw new FailedSaveRateException(List.of(new ErrorResponse(ErrorType.ERROR, "Saving rate failed")));
        }
    }
}

