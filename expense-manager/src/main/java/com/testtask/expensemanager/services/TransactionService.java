package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.TransactionCreateDto;
import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.dao.api.ITransactionDao;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Limit;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.dao.entyties.Transaction;
import com.testtask.expensemanager.services.api.*;
import com.testtask.expensemanager.services.exceptions.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionService implements ITransactionService {

    private static final String CURRENCY_NAME_FIELD_NAME = "currency_shortname";

    private static final String TRANS_SUM_FILED_NANE = "trans_sum";

    private static final String ACCOUNT_FROM_FIELD_NAME = "account_from";

    private static final String ACCOUNT_TO_FIELD_NAME = "account_to";

    private static final String DOLLAR_USA_CURRENCY_NAME = "USD";

    private final ITransactionDao transactionDao;

    private final ICurrencyService currencyService;

    private final ILimitService limitService;

    private final IRateService rateService;

    private final ConversionService conversionService;


    public TransactionService(ITransactionDao transactionDao,
                              ConversionService conversionService,
                              ICurrencyService currencyService,
                              ILimitService limitService,
                              IRateService rateService) {
        this.transactionDao = transactionDao;
        this.conversionService = conversionService;
        this.currencyService = currencyService;
        this.limitService = limitService;
        this.rateService = rateService;
    }


    @Transactional
    @Override
    public Transaction save(TransactionCreateDto transactionCreateDto) {
        validate(transactionCreateDto);

        Transaction transaction = this.conversionService.convert(transactionCreateDto, Transaction.class);

        UUID uuid = UUID.randomUUID();
        transaction.setUuid(uuid);
        transaction.setDateTime(LocalDateTime.now());

        String currencyName = transactionCreateDto.getCurrencyName();
        Currency currency = this.currencyService.get(currencyName);
        transaction.setCurrency(currency);

        Limit limit = this.limitService.getUpToDate(transactionCreateDto.getExpenseCategory());
        transaction.setLimit(limit);

        BigDecimal transSum = transactionCreateDto.getTransSum();
        if (!currencyName.equals(DOLLAR_USA_CURRENCY_NAME)) {
            transaction.setTransSumInUSD(calculateInUsd(currencyName, transSum));
        } else {
            transaction.setTransSumInUSD(transSum);
        }

        BigDecimal actualExpense = this.getActualExpense(transactionCreateDto.getExpenseCategory());
        boolean isExceeded = limit.getLimitSum().compareTo(actualExpense.add(transaction.getTransSumInUSD())) < 0;
        transaction.setExceeded(isExceeded);

        try {
            return this.transactionDao.save(transaction);
        } catch (Exception ex) {
            throw new FailedSaveTransactionException(List.of(new ErrorResponse(ErrorType.ERROR, "Saving transaction failed")));
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Transaction get(UUID uuid) {
        try {
            return this.transactionDao.findById(uuid).orElseThrow();
        } catch (NoSuchElementException ex) {
            throw new SuchTransactionNotExistsException(List.of(new ErrorResponse(ErrorType.ERROR, "There is no such transaction in database")));
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Transaction> get() {
        return this.transactionDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Transaction> getExceeded() {
        return this.transactionDao.findAllByIsExceeded(true);
    }

    @Transactional(readOnly = true)
    @Override
    public BigDecimal getActualExpense(ExpenseCategory expenseCategory) {
        BigDecimal actualExpense = this.transactionDao.findActualExpense(expenseCategory.toString());
        return actualExpense == null ? BigDecimal.ZERO : actualExpense;
    }


    private BigDecimal calculateInUsd(String currencyName, BigDecimal transSum) {
        BigDecimal transSumUsd;

        Rate rate = this.rateService.getFirstUpToDate(currencyName, DOLLAR_USA_CURRENCY_NAME);
        if (rate != null) {
            BigDecimal toUsdRate = rate.getValue();
            transSumUsd = transSum.multiply(toUsdRate).setScale(2, RoundingMode.HALF_UP);
        } else {
            rate = this.rateService.getFirstUpToDate(DOLLAR_USA_CURRENCY_NAME, currencyName);
            if (rate != null) {
                BigDecimal fromUsdRate = rate.getValue();
                transSumUsd = transSum.divide(fromUsdRate, RoundingMode.HALF_UP).setScale(2, RoundingMode.HALF_UP);
            } else {
                throw new FailedRateAccessException(List.of(new ErrorResponse(ErrorType.ERROR, "The server was unable to process the request correctly. Please try again later or contact administrator")));
            }
        }

        return transSumUsd;
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

        if (!errors.isEmpty()) {
            throw new InvalidTransactionBodyException(errors);
        }

    }


}
