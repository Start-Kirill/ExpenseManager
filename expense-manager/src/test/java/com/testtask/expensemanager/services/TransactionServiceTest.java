package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.TransactionCreateDto;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.dao.entyties.Transaction;
import com.testtask.expensemanager.services.api.ITransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private ITransactionService transactionService;

    @Test
    public void saveTest() {

        TransactionCreateDto transactionCreateDto = new TransactionCreateDto();

        transactionCreateDto.setAccountFrom("asdsad");
        transactionCreateDto.setAccountTo("adasda");
        transactionCreateDto.setCurrencyName("RUB");
        transactionCreateDto.setTransSum(new BigDecimal("70345.43"));
        transactionCreateDto.setDateTime(LocalDateTime.now());
        transactionCreateDto.setExpenseCategory(ExpenseCategory.PRODUCT);

        Transaction saved = this.transactionService.save(transactionCreateDto);

        System.out.println(saved);
    }
}
