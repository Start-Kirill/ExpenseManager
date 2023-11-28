package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.TransactionCreateDto;
import com.testtask.expensemanager.dao.api.ITransactionDao;
import com.testtask.expensemanager.dao.entyties.Transaction;
import com.testtask.expensemanager.services.api.ITransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    private final ITransactionDao transactionDao;

    public TransactionService(ITransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public List<Transaction> get() {
        return null;
    }

    @Override
    public Transaction save(TransactionCreateDto transactionCreateDto) {
        return null;
    }

    @Override
    public List<Transaction> getExceeded() {
        return null;
    }
}
