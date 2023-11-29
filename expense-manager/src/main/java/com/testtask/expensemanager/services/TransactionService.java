package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.TransactionCreateDto;
import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.dao.api.ITransactionDao;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Limit;
import com.testtask.expensemanager.dao.entyties.Transaction;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.ILimitService;
import com.testtask.expensemanager.services.api.ITransactionService;
import com.testtask.expensemanager.services.exceptions.FailedSaveTransactionException;
import com.testtask.expensemanager.services.exceptions.InvalidTransactionBodyException;
import com.testtask.expensemanager.services.exceptions.SuchCurrencyNotExistsException;
import com.testtask.expensemanager.services.exceptions.SuchTransactionNotExistsException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionService implements ITransactionService {

    private static final String CURRENCY_NAME_FIELD_NAME = "currency_shortname";

    private static final String TRANS_SUM_FILED_NANE = "trans_sum";

    private static final String ACCOUNT_FROM_FIELD_NAME = "account_from";

    private static final String ACCOUNT_TO_FIELD_NAME = "account_to";

    private static final String DATE_TIME_FIELD_NAME = "datetime";

    private final ITransactionDao transactionDao;

    private final ICurrencyService currencyService;

    private final ILimitService limitService;

    private final ConversionService conversionService;

    public TransactionService(ITransactionDao transactionDao,
                              ConversionService conversionService,
                              ICurrencyService currencyService,
                              ILimitService limitService) {
        this.transactionDao = transactionDao;
        this.conversionService = conversionService;
        this.currencyService = currencyService;
        this.limitService = limitService;
    }


    //    TODO подумать получше над этой задачей. Как составить правильный sql запрос. Возможно нужно хранить в базе эквивалентное значение в долларах ???
    @Transactional
    @Override
    public Transaction save(TransactionCreateDto transactionCreateDto) {
        validate(transactionCreateDto);

        Transaction transaction = this.conversionService.convert(transactionCreateDto, Transaction.class);

        UUID uuid = UUID.randomUUID();
        transaction.setUuid(uuid);

        String currencyName = transactionCreateDto.getCurrencyName();
        Currency currency = this.currencyService.get(currencyName);
        transaction.setCurrency(currency);

        Limit limit = this.limitService.getUpToDate();
        transaction.setLimit(limit);

        //        TODO может быть другая валюта
        BigDecimal rawReminder = this.limitService.getReminder(limit.getUuid());
        BigDecimal reminder = rawReminder.subtract(transaction.getTransSum());
        transaction.setExceeded(reminder.compareTo(BigDecimal.ZERO) < 0);


        try {
            return this.transactionDao.save(transaction);
        } catch (Exception ex) {
            throw new FailedSaveTransactionException(List.of(new ErrorResponse(ErrorType.ERROR, "Saving transaction failed")));
        }

    }

    @Override
    public Transaction get(UUID uuid) {
        try {
            return this.transactionDao.findById(uuid).orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new SuchTransactionNotExistsException(List.of(new ErrorResponse(ErrorType.ERROR, "There is no such transaction in database")));
        }
    }

    @Override
    public List<Transaction> get() {
        return this.transactionDao.findAll();
    }


    @Override
    public List<Transaction> getExceeded() {
        return this.transactionDao.findAllByIsExceeded(true);
    }


    private void validate(TransactionCreateDto transactionCreateDto) {

        Map<String, String> errors = new HashMap<>();

        String currencyName = transactionCreateDto.getCurrencyName();

        if (currencyName == null || currencyName.isEmpty()) {
            errors.put(CURRENCY_NAME_FIELD_NAME, "Field should be filled");
        }

        try {
            this.currencyService.get(currencyName);
        } catch (SuchCurrencyNotExistsException ex) {
            errors.put(CURRENCY_NAME_FIELD_NAME, ex.getErrors().get(0).getMessage());
        }

        BigDecimal transSum = transactionCreateDto.getTransSum();

        if (transSum == null) {
            errors.put(TRANS_SUM_FILED_NANE, "Filed should be filled");
        } else if (transSum.compareTo(BigDecimal.ZERO) < 0) {
            errors.put(TRANS_SUM_FILED_NANE, "Value must be positive");
        }

        String accountFrom = transactionCreateDto.getAccountFrom();
        if (accountFrom == null || accountFrom.isEmpty()) {
            errors.put(ACCOUNT_FROM_FIELD_NAME, "Filed should be filled");
        }

        String accountTO = transactionCreateDto.getAccountTo();
        if (accountTO == null || accountTO.isEmpty()) {
            errors.put(ACCOUNT_TO_FIELD_NAME, "Filed should be filled");
        }

        LocalDateTime dateTime = transactionCreateDto.getDateTime();
        if (dateTime.isAfter(LocalDateTime.now())) {
            errors.put(DATE_TIME_FIELD_NAME, "Such a time has not yet come");
        }

        if (!errors.isEmpty()) {
            throw new InvalidTransactionBodyException(errors);
        }

    }


}
