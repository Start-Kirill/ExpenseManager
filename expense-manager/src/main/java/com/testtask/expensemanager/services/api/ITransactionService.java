package com.testtask.expensemanager.services.api;

import com.testtask.expensemanager.core.dtos.TransactionCreateDto;
import com.testtask.expensemanager.dao.entyties.Transaction;

import java.util.List;

public interface ITransactionService extends ICRUDService<Transaction, TransactionCreateDto> {

    List<Transaction> getExceeded();
}
