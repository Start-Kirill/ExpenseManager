package com.testtask.expensemanager.services;

import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.services.api.IRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RateServiceTest {

    @Autowired
    private IRateService rateService;

    @Test
    public void getFirstUpToDateTest() {
        Rate firstUpToDate = this.rateService.getFirstUpToDate("USD", "KZT");
        System.out.println(firstUpToDate);
    }

}
