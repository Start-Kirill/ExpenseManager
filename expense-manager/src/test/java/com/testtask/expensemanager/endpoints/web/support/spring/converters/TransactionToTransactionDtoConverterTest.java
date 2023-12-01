package com.testtask.expensemanager.endpoints.web.support.spring.converters;

import com.testtask.expensemanager.core.dtos.RateDto;
import com.testtask.expensemanager.core.dtos.TransactionDto;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Limit;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.dao.entyties.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionToTransactionDtoConverterTest {

    @InjectMocks
    private TransactionToTransactionDtoConverter converter;

    @Test
    public void shouldConvert() {
        UUID uuid = UUID.randomUUID();
        LocalDateTime dateTime = mock(LocalDateTime.class);
        Currency currency = mock(Currency.class);
        when(currency.getName()).thenReturn("USD");
        BigDecimal value = mock(BigDecimal.class);
        String accountFrom = "from";
        String accountTo = "to";
        Limit limit = mock(Limit.class);
        when(limit.getLimitSum()).thenReturn(value);
        when(limit.getDateTimeCreate()).thenReturn(dateTime);
        when(limit.getCurrency()).thenReturn(currency);

        Transaction transaction = new Transaction(uuid, dateTime, currency, ExpenseCategory.PRODUCT, accountFrom, accountTo, value, value, limit, false);
        TransactionDto transactionDto = new TransactionDto(uuid, dateTime, value, "USD", ExpenseCategory.PRODUCT, accountFrom, accountTo, false, value, dateTime, "USD");

        TransactionDto actual = this.converter.convert(transaction);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual, transactionDto);
    }
}
