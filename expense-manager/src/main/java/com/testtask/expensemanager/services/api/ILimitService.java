package com.testtask.expensemanager.services.api;

import com.testtask.expensemanager.core.dtos.LimitCreateDto;
import com.testtask.expensemanager.core.dtos.LimitUpdateDto;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.dao.entyties.Limit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface ILimitService extends ICRUDService<Limit, LimitCreateDto> {

    Limit getUpToDate();

    BigDecimal getReminder(UUID uuid);
}
