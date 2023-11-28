package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.CurrencyCreateDto;
import com.testtask.expensemanager.dao.api.ICurrencyDao;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.services.api.ICurrencyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyDao currencyDao;

    public CurrencyService(ICurrencyDao currencyDao) {
        this.currencyDao = currencyDao;
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
