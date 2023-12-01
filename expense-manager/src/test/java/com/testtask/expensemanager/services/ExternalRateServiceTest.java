package com.testtask.expensemanager.services;

import com.testtask.expensemanager.config.property.RatesProperty;
import com.testtask.expensemanager.core.dtos.ExternalRateCreateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateDto;
import com.testtask.expensemanager.services.api.IRateClient;
import com.testtask.expensemanager.services.exceptions.FailedRateAccessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExternalRateServiceTest {


    private ExternalRateService externalRateService;

    @Mock
    private RatesProperty ratesProperty;

    @Mock
    private IRateClient rateClient;

    @Mock
    private RatesProperty.Twelvedata twelvedataProps;

    @BeforeEach
    public void setUp() {
        when(this.ratesProperty.getTwelvedata()).thenReturn(twelvedataProps);
        this.externalRateService = new ExternalRateService(this.rateClient, this.ratesProperty);
    }

    @Test
    public void shouldGet() {
        when(this.twelvedataProps.getKey()).thenReturn("key");
        Map<String, ExternalRateDto> externalRates = new HashMap<>();
        when(this.rateClient.get(anyString(), anyString(), any(LocalDate.class), any(LocalDate.class), anyString())).thenReturn(externalRates);

        ExternalRateCreateDto externalRateCreateDto = new ExternalRateCreateDto(new ArrayList<>(), mock(LocalDate.class), mock(LocalDate.class));
        Map<String, ExternalRateDto> actual = this.externalRateService.get(externalRateCreateDto);

        assertNotNull(actual);
        assertEquals(actual, externalRates);
    }

    @Test
    public void shouldThrowWhileGet() {
        when(this.twelvedataProps.getKey()).thenReturn("key");
        when(this.rateClient.get(anyString(), anyString(), any(LocalDate.class), any(LocalDate.class), anyString())).thenThrow(RuntimeException.class);

        ExternalRateCreateDto externalRateCreateDto = new ExternalRateCreateDto(new ArrayList<>(), mock(LocalDate.class), mock(LocalDate.class));

        assertThrows(FailedRateAccessException.class, () -> this.externalRateService.get(externalRateCreateDto));
    }

    @Test
    public void shouldGetLastThirty() {
        when(this.twelvedataProps.getKey()).thenReturn("key");
        Map<String, ExternalRateDto> externalRates = new HashMap<>();
        when(this.rateClient.get(anyString(), anyString(), any(LocalDate.class), any(LocalDate.class), anyString())).thenReturn(externalRates);


        Map<String, ExternalRateDto> actual = this.externalRateService.getLastThirty(new ArrayList<Pair<String, String>>());

        assertNotNull(actual);
        assertEquals(actual, externalRates);
    }

    @Test
    public void shouldThrowWhileGetLastThirty() {
        when(this.twelvedataProps.getKey()).thenReturn("key");
        when(this.rateClient.get(anyString(), anyString(), any(LocalDate.class), any(LocalDate.class), anyString())).thenThrow(RuntimeException.class);

        assertThrows(FailedRateAccessException.class, () -> this.externalRateService.getLastThirty(new ArrayList<>()));
    }

}
