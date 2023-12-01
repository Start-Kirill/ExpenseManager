package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.LimitCreateDto;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.dao.api.ILimitDao;
import com.testtask.expensemanager.dao.entyties.Limit;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.exceptions.InvalidLimitBodyException;
import com.testtask.expensemanager.services.exceptions.SuchLimitNotExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LimitServiceTest {

    @InjectMocks
    private LimitService limitService;

    @Mock
    private ILimitDao limitDao;

    @Mock
    private ConversionService conversionService;

    @Mock
    private ICurrencyService currencyService;

    @Test
    public void shouldSave() {
        when(this.conversionService.convert(any(LimitCreateDto.class), eq(Limit.class))).thenReturn(mock(Limit.class));
        Limit limit = mock(Limit.class);
        when(this.limitDao.save(any(Limit.class))).thenReturn(limit);

        Limit actual = this.limitService.save(new LimitCreateDto(BigDecimal.valueOf(1000.00), ExpenseCategory.PRODUCT));

        assertNotNull(actual);
        assertEquals(actual, limit);
    }

    @Test
    public void shouldThrowWhileSave() {
        assertThrows(InvalidLimitBodyException.class, () -> this.limitService.save(new LimitCreateDto()));
    }

    @Test
    public void shouldGetByUuid() {
        Limit limit = mock(Limit.class);
        when(this.limitDao.findById(any(UUID.class))).thenReturn(Optional.of(limit));

        Limit actual = this.limitService.get(mock(UUID.class));

        assertNotNull(actual);
        assertEquals(actual, limit);
    }

    @Test
    public void shouldThrowWhileGetByUuid() {
        when(this.limitDao.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(SuchLimitNotExistsException.class, () -> this.limitService.get(mock(UUID.class)));
    }

    @Test
    public void shouldGet() {
        List<Limit> limits = new ArrayList<>();
        when(this.limitDao.findAll()).thenReturn(limits);

        List<Limit> actual = this.limitService.get();

        assertNotNull(actual);
        assertEquals(actual, limits);
    }

    @Test
    public void shouldGetUpToDate() {
        Limit limit = new Limit();
        when(this.limitDao.findTopByExpenseCategoryOrderByDateTimeCreateDesc(any(ExpenseCategory.class))).thenReturn(Optional.of(limit));

        Limit actual = this.limitService.getUpToDate(ExpenseCategory.PRODUCT);

        assertNotNull(actual);
        assertEquals(actual, limit);
    }

    @Test
    public void shouldThrowWhileGetUpToDate() {
        when(this.limitDao.findTopByExpenseCategoryOrderByDateTimeCreateDesc(any(ExpenseCategory.class))).thenReturn(Optional.empty());

        assertThrows(SuchLimitNotExistsException.class, () -> this.limitService.getUpToDate(ExpenseCategory.PRODUCT));
    }




}
