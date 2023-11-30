package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.LimitCreateDto;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.services.api.ILimitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class LimitServiceTest {

    @Autowired
    private ILimitService limitService;

    @Test
    public void createTest(){
        this.limitService.save(new LimitCreateDto(BigDecimal.valueOf(1000.00), ExpenseCategory.PRODUCT));
    }
}
