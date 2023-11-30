package com.testtask.expensemanager.services.api;

import com.testtask.expensemanager.core.dtos.LimitCreateDto;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.dao.entyties.Limit;

public interface ILimitService extends ICRUDService<Limit, LimitCreateDto> {

    Limit getUpToDate(ExpenseCategory expenseCategory);

}
