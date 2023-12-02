package com.testtask.expensemanager.dao.api;

import com.testtask.expensemanager.core.enums.RateStatus;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IRateDao extends JpaRepository<Rate, UUID> {
    Rate findTopByOrderByDatetimeDesc();

    Rate findTopByFirstCurrencyAndSecondCurrencyOrderByDatetimeDesc(Currency firstCurrency, Currency secondCurrency);

    List<Rate> findAllByStatus(RateStatus status);


}
