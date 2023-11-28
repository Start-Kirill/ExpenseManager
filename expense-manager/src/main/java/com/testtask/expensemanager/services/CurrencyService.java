package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.CurrencyCreateDto;
import com.testtask.expensemanager.dao.api.ICurrencyDao;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.services.api.ICurrencyService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyDao currencyDao;

    private final ConversionService conversionService;

    public CurrencyService(ICurrencyDao currencyDao,
                           ConversionService conversionService) {
        this.currencyDao = currencyDao;
        this.conversionService = conversionService;
    }

    @Override
    public List<Currency> get() {
        return null;
    }

    @Override
    public Currency save(CurrencyCreateDto currencyCreateDto) {
        return null;
    }
}
