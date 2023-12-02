package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.IRateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.util.Pair;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyRatePairsSchedulerServiceTest {

    @InjectMocks
    private CurrencyRatePairsSchedulerService currencyRatePairsSchedulerService;

    @Mock
    private ICurrencyService currencyService;

    @Mock
    private IRateService rateService;

    @Mock
    private ConversionService conversionService;

    @Test
    public void shouldExecute() {
        List<Pair<String, String>> pairs = List.of(mock(Pair.class));
        when(this.currencyService.getCurrencyPairs()).thenReturn(pairs);
        when(this.conversionService.convert(any(Pair.class), eq(RateCreateDto.class))).thenReturn(mock(RateCreateDto.class));

        Assertions.assertDoesNotThrow(() -> this.currencyRatePairsSchedulerService.execute());
        verify(this.rateService).saveAll(any(List.class));
    }


}
