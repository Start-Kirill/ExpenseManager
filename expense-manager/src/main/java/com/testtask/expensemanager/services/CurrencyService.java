package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.CurrencyCreateDto;
import com.testtask.expensemanager.dao.api.ICurrencyDao;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.services.api.ICurrencyService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyDao currencyDao;

    private final ConversionService conversionService;

    public CurrencyService(ICurrencyDao currencyDao,
                           ConversionService conversionService) {
        this.currencyDao = currencyDao;
        this.conversionService = conversionService;
    }

    @Transactional(readOnly = true)
    @Override
    public Currency get(UUID uuid) {
        try {
            return this.currencyDao.findById(uuid).orElseThrow();
//            TODO
        } catch (Exception ex) {
            throw new RuntimeException();
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Currency get(String name) {
        try {
            return this.currencyDao.findByName(name).orElseThrow();
//            TODO
        } catch (Exception ex) {
            throw new RuntimeException();
        }

    }

    @Transactional(readOnly = true)
    @Override
    public List<Currency> get() {
        return this.currencyDao.findAll();
    }

    @Transactional
    @Override
    public Currency save(CurrencyCreateDto currencyCreateDto) {

        validate(currencyCreateDto);

        Currency currency = this.conversionService.convert(currencyCreateDto, Currency.class);
        currency.setUuid(UUID.randomUUID());
        try {
            return this.currencyDao.save(currency);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }




    //    TODO
    private void validate(CurrencyCreateDto currencyCreateDto) {


    }
}
