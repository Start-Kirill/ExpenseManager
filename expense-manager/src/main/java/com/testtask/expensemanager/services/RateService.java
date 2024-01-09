package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.enums.RateStatus;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.dao.api.IRateDao;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.IExternalRateService;
import com.testtask.expensemanager.services.api.IRateService;
import com.testtask.expensemanager.services.exceptions.FailedSaveRateException;
import com.testtask.expensemanager.services.exceptions.RateDateTimeUpdatesNotMatchException;
import com.testtask.expensemanager.services.exceptions.SuchRateNotExistsException;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class RateService implements IRateService {

    private static final String FAILED_SAVE_MESSAGE = "Saving rate failed";

    private static final String RATE_NOT_STORE_MESSAGE = "There is no such rate in database";

    private static final String VERSIONS_NOT_MATCH_MESSAGE = "Updating rate failed. Rates version do not match. Please wait a couple of minutes or contact administrator";

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

    @Transactional
    @Override
    public Rate save(RateCreateDto rateCreateDto) {
        Rate rate = this.conversionService.convert(rateCreateDto, Rate.class);

        rate.setUuid(UUID.randomUUID());
        rate.setFirstCurrency(this.currencyService.get(rateCreateDto.getFirstCurrencyName()));
        rate.setSecondCurrency(this.currencyService.get(rateCreateDto.getSecondCurrencyName()));

        try {
            return this.rateDao.saveAndFlush(rate);
        } catch (Exception ex) {
            throw new FailedSaveRateException(FAILED_SAVE_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, FAILED_SAVE_MESSAGE)));
        }
    }

    @Transactional
    @Override
    public List<Rate> saveAll(List<RateCreateDto> rateCreateDto) {

        List<Rate> rates = rateCreateDto.stream().map(rc -> {
            Rate rate = this.conversionService.convert(rc, Rate.class);
            rate.setUuid(UUID.randomUUID());
            rate.setFirstCurrency(this.currencyService.get(rc.getFirstCurrencyName()));
            rate.setSecondCurrency(this.currencyService.get(rc.getSecondCurrencyName()));
            LocalDateTime now = LocalDateTime.now();
            rate.setDtCreate(now);
            rate.setDtUpdate(now);
            rate.setAttempt(0L);
            return rate;
        }).toList();

        try {
            return this.rateDao.saveAllAndFlush(rates);
        } catch (Exception ex) {
            throw new FailedSaveRateException(FAILED_SAVE_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, FAILED_SAVE_MESSAGE)));
        }
    }

    @Transactional
    @Override
    public Rate update(Rate rate) {
        Rate actualRate = this.get(rate.getUuid());
        LocalDateTime actualDtUpdate = actualRate.getDtUpdate();
        LocalDateTime dtUpdate = rate.getDtUpdate();
        if (!actualDtUpdate.isEqual(dtUpdate)) {
            throw new RateDateTimeUpdatesNotMatchException(VERSIONS_NOT_MATCH_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, VERSIONS_NOT_MATCH_MESSAGE)));
        }
        actualRate.setValue(rate.getValue());
        actualRate.setStatus(rate.getStatus());
        actualRate.setAttempt(rate.getAttempt());

        try {
            return this.rateDao.saveAndFlush(actualRate);
        } catch (OptimisticLockingFailureException ex) {
            throw new RateDateTimeUpdatesNotMatchException(VERSIONS_NOT_MATCH_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, VERSIONS_NOT_MATCH_MESSAGE)));
        }

    }

    @Transactional
    @Override
    public List<Rate> updateAll(List<Rate> rates) {
        return rates.stream().map(this::update).toList();
    }


    @Override
    public List<Rate> getByStatus(RateStatus status) {
        return this.rateDao.findAllByStatus(status);
    }


    @Transactional(readOnly = true)
    @Override
    public Rate get(UUID uuid) {
        try {
            return this.rateDao.findById(uuid).orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new SuchRateNotExistsException(RATE_NOT_STORE_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, RATE_NOT_STORE_MESSAGE)));
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Rate> get() {
        return this.rateDao.findAll();
    }


    @Transactional(readOnly = true)
    @Override
    public boolean isEmpty() {
        return this.rateDao.count() == 0;
    }


    @Transactional(readOnly = true)
    @Override
    public Rate getFirstUpToDate() {
        return this.rateDao.findTopByOrderByDatetimeDesc();
    }

    @Transactional(readOnly = true)
    @Override
    public Rate getFirstUpToDate(String firstCurrencyName, String secondCurrencyName) {
        Currency firstCurrency = this.currencyService.get(firstCurrencyName);
        Currency secondCurrency = this.currencyService.get(secondCurrencyName);
        Rate rate = this.rateDao.findTopByFirstCurrencyAndSecondCurrencyAndStatusOrderByDatetimeDesc(firstCurrency, secondCurrency, RateStatus.DONE);
        return rate;
    }


}

