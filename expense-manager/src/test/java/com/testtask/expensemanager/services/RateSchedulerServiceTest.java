package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.ExternalRateCreateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateDto;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.IExternalRateService;
import com.testtask.expensemanager.services.api.IRateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RateSchedulerServiceTest {

    @InjectMocks
    private RateSchedulerService rateSchedulerService;

    @Mock
    private ICurrencyService currencyService;

    @Mock
    private IExternalRateService externalRateService;

    @Mock
    private IRateService rateService;

    @Test
    public void shouldExecuteFirstCase() {
        Rate rate = mock(Rate.class);
        LocalDateTime localDateTime = mock(LocalDateTime.class);
        when(localDateTime.toLocalDate()).thenReturn(LocalDate.now().minusDays(1));
        when(rate.getDate()).thenReturn(localDateTime);
        when(this.rateService.getFirstUpToDate()).thenReturn(rate);
        List<Currency> currencies = new ArrayList<>();
        when(this.currencyService.get()).thenReturn(currencies);
        Map<String, ExternalRateDto> externalRates = new HashMap<>();
        when(this.externalRateService.get(any(ExternalRateCreateDto.class))).thenReturn(externalRates);


        Assertions.assertDoesNotThrow(() -> this.rateSchedulerService.execute());
        verify(this.rateService).saveAll(any(List.class));
    }

    @Test
    public void shouldExecuteSecondCase() {
        when(this.rateService.getFirstUpToDate()).thenReturn(null);
        List<Currency> currencies = new ArrayList<>();
        when(this.currencyService.get()).thenReturn(currencies);
        Map<String, ExternalRateDto> externalRates = new HashMap<>();
        when(this.externalRateService.getLastThirty(any(List.class))).thenReturn(externalRates);


        Assertions.assertDoesNotThrow(() -> this.rateSchedulerService.execute());
        verify(this.rateService).saveAll(any(List.class));
    }


}
