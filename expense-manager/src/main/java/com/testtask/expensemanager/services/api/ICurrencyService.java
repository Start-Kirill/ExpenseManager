package com.testtask.expensemanager.services.api;

import com.testtask.expensemanager.core.dtos.CurrencyCreateDto;
import com.testtask.expensemanager.dao.entyties.Currency;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ICurrencyService extends ICRUDService<Currency, CurrencyCreateDto> {

    Currency get(String name);

    List<Pair<String, String>> getCurrencyPairs();
}
