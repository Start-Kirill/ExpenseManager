package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.TransactionCreateDto;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.dao.api.ITransactionDao;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Limit;
import com.testtask.expensemanager.dao.entyties.Transaction;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.ILimitService;
import com.testtask.expensemanager.services.api.IRateSchedulerService;
import com.testtask.expensemanager.services.api.IRateService;
import com.testtask.expensemanager.services.exceptions.InvalidTransactionBodyException;
import com.testtask.expensemanager.services.exceptions.SuchTransactionNotExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    private static final String DEFAULT_CURRENCY_NAME = "USD";

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private ITransactionDao transactionDao;

    @Mock
    private ICurrencyService currencyService;

    @Mock
    private ILimitService limitService;

    @Mock
    private IRateService rateService;

    @Mock
    private ConversionService conversionService;

    @Mock
    private IRateSchedulerService rateSchedulerService;

    @Test
    public void shouldSaveTest() {
        final Transaction transaction = mock(Transaction.class);
        when(transaction.getTransSumInUSD()).thenReturn(BigDecimal.valueOf(2));
        when(this.conversionService.convert(any(TransactionCreateDto.class), eq(Transaction.class)))
                .thenReturn(transaction);
        final Currency currency = mock(Currency.class);
        when(this.currencyService.get(anyString())).thenReturn(currency);
        final Limit limit = mock(Limit.class);
        when(limit.getLimitSum()).thenReturn(BigDecimal.valueOf(100));
        when(this.limitService.getUpToDate(any(ExpenseCategory.class))).thenReturn(limit);
        when(this.transactionDao.save(any(Transaction.class))).thenReturn(transaction);
        when(this.transactionDao.findActualExpense(anyString())).thenReturn(BigDecimal.valueOf(5));

        final Transaction actual = this.transactionService.save(generateCorrect());

        assertNotNull(actual);
        assertEquals(actual, transaction);
    }

    @Test
    public void shouldTrowWhileSaveTest() {
        final Currency currency = mock(Currency.class);
        lenient().when(this.currencyService.get(anyString())).thenReturn(currency);

        assertThrows(InvalidTransactionBodyException.class, () -> this.transactionService.save(mock(TransactionCreateDto.class)));
    }

    @Test
    public void shouldGeByUuid() {
        final Transaction transaction = mock(Transaction.class);
        when(this.transactionDao.findById(any(UUID.class))).thenReturn(Optional.of(transaction));

        Transaction actual = this.transactionService.get(mock(UUID.class));

        assertNotNull(actual);
        assertEquals(transaction, actual);
    }

    @Test
    public void shouldThrowWhileGetByUuid() {
        when(this.transactionDao.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(SuchTransactionNotExistsException.class, () -> this.transactionService.get(mock(UUID.class)));
    }

    @Test
    public void shouldGet() {
        List<Transaction> transactions = new ArrayList<>();
        when(this.transactionDao.findAll()).thenReturn(transactions);

        List<Transaction> actual = this.transactionService.get();

        assertNotNull(actual);
        assertEquals(actual, transactions);
    }

    @Test
    public void shouldGetExceed() {
        List<Transaction> transactions = new ArrayList<>();
        when(this.transactionDao.findAllByIsExceeded(true)).thenReturn(transactions);

        List<Transaction> actual = this.transactionService.getExceeded();

        assertNotNull(actual);
        assertEquals(actual, transactions);
    }

    @Test
    public void shouldGetActualExpense() {
        BigDecimal expense = mock(BigDecimal.class);
        when(this.transactionDao.findActualExpense(anyString())).thenReturn(expense);

        BigDecimal actualExpense = this.transactionService.getActualExpense(mock(ExpenseCategory.class));

        assertNotNull(actualExpense);
        assertEquals(actualExpense, expense);
    }


    private TransactionCreateDto generateCorrect() {
        return new TransactionCreateDto(BigDecimal.valueOf(1),
                DEFAULT_CURRENCY_NAME,
                ExpenseCategory.PRODUCT,
                "123", "321",
                LocalDateTime.now());
    }


}
