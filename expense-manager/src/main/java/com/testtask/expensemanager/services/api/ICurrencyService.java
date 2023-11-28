package com.testtask.expensemanager.services.api;

import com.testtask.expensemanager.core.dtos.CurrencyCreateDto;
import com.testtask.expensemanager.dao.entyties.Currency;

public interface ICurrencyService extends ICRUDService<Currency, CurrencyCreateDto> {
}
