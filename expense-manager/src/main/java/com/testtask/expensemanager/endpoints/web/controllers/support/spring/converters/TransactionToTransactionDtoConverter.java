package com.testtask.expensemanager.endpoints.web.controllers.support.spring.converters;

import com.testtask.expensemanager.core.dtos.TransactionDto;
import com.testtask.expensemanager.dao.entyties.Transaction;
import org.springframework.core.convert.converter.Converter;

public class TransactionToTransactionDtoConverter implements Converter<Transaction, TransactionDto> {
    @Override
    public TransactionDto convert(Transaction source) {
        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setUuid(source.getUuid());
        transactionDto.setAccountTo(source.getAccountTo());
        transactionDto.setAccountFrom(source.getAccountFrom());
        transactionDto.setCurrencyName(source.getCurrency().getName());
        transactionDto.setDateTime(source.getDateTime());
        transactionDto.setExpenseCategory(source.getExpenseCategory());
        transactionDto.setTransSum(source.getTransSum());
        transactionDto.setLimitExceeded(source.isExceeded());

        transactionDto.setLimitSum(source.getLimit().getLimitSum());
        transactionDto.setLimitDateTime(source.getLimit().getDateTimeCreate());
        transactionDto.setLimitCurrencyName(source.getLimit().getCurrency().getName());


        return transactionDto;
    }
}
