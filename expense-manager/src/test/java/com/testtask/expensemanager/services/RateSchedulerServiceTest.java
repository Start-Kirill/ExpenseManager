package com.testtask.expensemanager.services;

import com.testtask.expensemanager.dao.entyties.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class RateSchedulerServiceTest {

    @Autowired
    private RateSchedulerService rateSchedulerService;

    @Test
    public void test() {
            rateSchedulerService.execute();
    }

    private List<Currency> getCurrencies() {
        List<Currency> currencies = new ArrayList<>();

        currencies.add(new Currency(UUID.randomUUID(), "USD"));
        currencies.add(new Currency(UUID.randomUUID(), "RUB"));
        currencies.add(new Currency(UUID.randomUUID(), "BYN"));
        currencies.add(new Currency(UUID.randomUUID(), "KZT"));
        currencies.add(new Currency(UUID.randomUUID(), "EUR"));


        return currencies;
    }

}
