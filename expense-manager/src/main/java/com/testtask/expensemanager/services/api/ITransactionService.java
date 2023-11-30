package com.testtask.expensemanager.services.api;

import com.testtask.expensemanager.core.dtos.TransactionCreateDto;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.dao.entyties.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionService extends ICRUDService<Transaction, TransactionCreateDto> {

    List<Transaction> getExceeded();

    BigDecimal getActualExpense(ExpenseCategory expenseCategory);

}
