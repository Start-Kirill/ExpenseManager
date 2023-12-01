package com.testtask.expensemanager.services.support.spring.converters;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.core.dtos.TransactionCreateDto;
import com.testtask.expensemanager.core.dtos.TransactionDto;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.dao.entyties.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class TransactionCreateDtoToTransactionConverterTest {

    @InjectMocks
    private TransactionCreateDtoToTransactionConverter converter;

    @Test
    public void shouldConvert() {
        BigDecimal bigDecimal = mock(BigDecimal.class);
        String account = "account";

        TransactionCreateDto transactionDto = new TransactionCreateDto();
        transactionDto.setAccountFrom(account);
        transactionDto.setAccountTo(account);
        transactionDto.setExpenseCategory(ExpenseCategory.PRODUCT);
        transactionDto.setTransSum(bigDecimal);

        Transaction transaction = new Transaction();
        transaction.setAccountFrom(account);
        transaction.setAccountTo(account);
        transaction.setExpenseCategory(ExpenseCategory.PRODUCT);
        transaction.setTransSum(bigDecimal);

        Transaction actual = this.converter.convert(transactionDto);

        assertNotNull(actual);
        assertEquals(actual, transaction);
    }
}
