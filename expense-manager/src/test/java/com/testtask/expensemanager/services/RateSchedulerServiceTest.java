package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.ExternalRateCreateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateDto;
import com.testtask.expensemanager.core.enums.RateStatus;
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
import org.springframework.core.convert.ConversionService;
import org.springframework.data.util.Pair;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Mock
    private ConversionService conversionService;

    @Test
    public void shouldExecuteFirstCase() {
        when(this.rateService.isEmpty()).thenReturn(true);
        Pair pair = mock(Pair.class);
        when(this.currencyService.getCurrencyPairs()).thenReturn(List.of(pair));
        Map<String, ExternalRateDto> externalRates = new HashMap<>();
        when(this.externalRateService.getLastThirty(any(List.class))).thenReturn(externalRates);


        Assertions.assertDoesNotThrow(() -> this.rateSchedulerService.execute());
        verify(this.rateService).saveAll(any(List.class));
    }

    @Test
    public void shouldExecuteSecondCase() {
        when(this.rateService.isEmpty()).thenReturn(false);
        Rate rate = mock(Rate.class);
        Currency currency = mock(Currency.class);
        when(currency.getName()).thenReturn("Name");
        when(rate.getFirstCurrency()).thenReturn(currency);
        when(rate.getSecondCurrency()).thenReturn(currency);
        when(rate.getDatetime()).thenReturn(LocalDateTime.now());
        List<Rate> createdRates = List.of(rate);
        when(this.rateService.getByStatus(any(RateStatus.class))).thenReturn(createdRates);
        ExternalRateCreateDto externalRateCreateDto = mock(ExternalRateCreateDto.class);
        when(this.conversionService.convert(any(List.class), eq(ExternalRateCreateDto.class))).thenReturn(externalRateCreateDto);
        Map<String, ExternalRateDto> externalRateDtoMap = new HashMap<>();
        when(this.externalRateService.get(any(ExternalRateCreateDto.class))).thenReturn(externalRateDtoMap);

        Assertions.assertDoesNotThrow(() -> this.rateSchedulerService.execute());
        verify(this.rateService).updateAll(any(List.class));
    }


}
