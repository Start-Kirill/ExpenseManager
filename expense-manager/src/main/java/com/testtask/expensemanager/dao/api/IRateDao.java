package com.testtask.expensemanager.dao.api;

import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRateDao extends JpaRepository<Rate, UUID> {
    Rate findTopByOrderByDateDesc();

    Rate findTopByFirstCurrencyAndSecondCurrencyOrderByDateDesc(Currency firstCurrency, Currency secondCurrency);

}
