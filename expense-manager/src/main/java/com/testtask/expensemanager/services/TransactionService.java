package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.TransactionCreateDto;
import com.testtask.expensemanager.dao.api.ITransactionDao;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Limit;
import com.testtask.expensemanager.dao.entyties.Transaction;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.ILimitService;
import com.testtask.expensemanager.services.api.ITransactionService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TransactionService implements ITransactionService {

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
//            TODO
        } catch (Exception ex) {
            throw new RuntimeException();
        }

    }

    @Override
    public Transaction get(UUID uuid) {
        try {
            return this.transactionDao.findById(uuid).orElseThrow();
//            TODO
        } catch (NoSuchElementException ex) {
            throw new RuntimeException();
        }


    }

    @Override
    public List<Transaction> get() {
        return this.transactionDao.findAll();
    }


    @Override
    public List<Transaction> getExceeded() {
        return this.transactionDao.findAllByIsExceeded();
    }

    //    TODO
    private void validate(TransactionCreateDto transactionCreateDto) {
    }


}
