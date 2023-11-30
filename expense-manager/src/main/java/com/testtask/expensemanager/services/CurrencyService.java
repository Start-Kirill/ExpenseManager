package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.CurrencyCreateDto;
import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.dao.api.ICurrencyDao;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.exceptions.FailedSaveCurrencyException;
import com.testtask.expensemanager.services.exceptions.InvalidCurrencyBodyException;
import com.testtask.expensemanager.services.exceptions.SuchCurrencyNotExistsException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CurrencyService implements ICurrencyService {

    private static final String CURRENCY_NOT_EXIST_MESSAGE = "Currency with such name does not exist";

    private static final String CURRENCY_NOT_STORED_MESSAGE = "Currency with such name is note stored in database";

    private static final String FAILED_SAVE_MESSAGE = "Saving currency failed";

    private static final String NAME_FIELD_NAME = "name";
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
        } catch (NoSuchElementException ex) {
            throw new SuchCurrencyNotExistsException(CURRENCY_NOT_EXIST_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, CURRENCY_NOT_EXIST_MESSAGE)));
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Currency get(String name) {
        try {
            return this.currencyDao.findByName(name).orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new SuchCurrencyNotExistsException(CURRENCY_NOT_STORED_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, CURRENCY_NOT_STORED_MESSAGE)));
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
            throw new FailedSaveCurrencyException(FAILED_SAVE_MESSAGE, List.of(new ErrorResponse(ErrorType.ERROR, FAILED_SAVE_MESSAGE)));
        }

    }


    private void validate(CurrencyCreateDto currencyCreateDto) {

        Map<String, String> errors = new HashMap<>();

        String name = currencyCreateDto.getName();
        if (name == null || name.isEmpty()) {
            errors.put(NAME_FIELD_NAME, "Filed should be filled");
        }

        if (!errors.isEmpty()) {
            throw new InvalidCurrencyBodyException(errors);
        }
    }
}
