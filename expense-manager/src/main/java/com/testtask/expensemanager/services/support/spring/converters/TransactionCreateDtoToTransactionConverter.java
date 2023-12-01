package com.testtask.expensemanager.services.support.spring.converters;

import com.testtask.expensemanager.core.dtos.TransactionCreateDto;
import com.testtask.expensemanager.dao.entyties.Transaction;
import org.springframework.core.convert.converter.Converter;

public class TransactionCreateDtoToTransactionConverter implements Converter<TransactionCreateDto, Transaction> {

    @Override
    public Transaction convert(TransactionCreateDto source) {

        Transaction transaction = new Transaction();

        transaction.setAccountFrom(source.getAccountFrom());
        transaction.setAccountTo(source.getAccountTo());
        transaction.setExpenseCategory(source.getExpenseCategory());
        transaction.setTransSum(source.getTransSum());


        return transaction;
    }
}
