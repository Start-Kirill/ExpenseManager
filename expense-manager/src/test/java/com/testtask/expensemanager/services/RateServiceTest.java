package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.dao.api.IRateDao;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.IExternalRateService;
import com.testtask.expensemanager.services.exceptions.FailedSaveRateException;
import com.testtask.expensemanager.services.exceptions.SuchRateNotExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RateServiceTest {

    @InjectMocks
    private RateService rateService;

    @Mock
    private IRateDao rateDao;

    @Mock
    private ConversionService conversionService;

    @Mock
    private ICurrencyService currencyService;

    @Mock
    private IExternalRateService externalRateService;

    @Test
    public void shouldSave() {
        Rate rate = mock(Rate.class);
        when(this.conversionService.convert(any(RateCreateDto.class), eq(Rate.class))).thenReturn(rate);
        when(this.rateDao.saveAndFlush(any(Rate.class))).thenReturn(rate);

        RateCreateDto rateCreateDto = mock(RateCreateDto.class);
        Rate actual = this.rateService.save(rateCreateDto);

        assertNotNull(actual);
        assertEquals(actual, rate);
    }

    @Test
    public void shouldThrowWhileSave() {
        Rate rate = mock(Rate.class);
        when(this.conversionService.convert(any(RateCreateDto.class), eq(Rate.class))).thenReturn(rate);
        when(this.rateDao.saveAndFlush(any(Rate.class))).thenThrow(RuntimeException.class);

        assertThrows(FailedSaveRateException.class, () -> this.rateService.save(mock(RateCreateDto.class)));
    }

    @Test
    public void shouldSaveAll() {
        List<Rate> rates = new ArrayList<Rate>();
        when(this.rateDao.saveAllAndFlush(any(List.class))).thenReturn(rates);

        List<RateCreateDto> rateCreateDtos = new ArrayList<>();
        List<Rate> actual = this.rateService.saveAll(rateCreateDtos);

        assertNotNull(actual);
        assertEquals(actual, rates);
    }

    @Test
    public void shouldThrowWhileSaveAll() {
        List<Rate> rates = new ArrayList<Rate>();
        when(this.rateDao.saveAllAndFlush(any(List.class))).thenThrow(RuntimeException.class);

        List<RateCreateDto> rateCreateDtos = new ArrayList<>();

        assertThrows(FailedSaveRateException.class, () -> this.rateService.saveAll(rateCreateDtos));
    }

    @Test
    public void shouldGetByUuid() {
        Rate rate = mock(Rate.class);
        when(this.rateDao.findById(any(UUID.class))).thenReturn(Optional.of(rate));

        Rate actual = this.rateService.get(mock(UUID.class));

        assertNotNull(actual);
        assertEquals(actual, rate);
    }

    @Test
    public void shouldTrowWhileGetByUuid() {
        when(this.rateDao.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(SuchRateNotExistsException.class, () -> this.rateService.get(mock(UUID.class)));
    }

    @Test
    public void shouldGet() {
        List<Rate> rates = new ArrayList<Rate>();
        when(this.rateDao.findAll()).thenReturn(rates);

        List<Rate> actual = this.rateService.get();

        assertNotNull(actual);
        assertEquals(actual, rates);
    }

    @Test
    public void shouldGetFirstUpToDate() {
        Rate rate = mock(Rate.class);
        when(this.rateDao.findTopByOrderByDateDesc()).thenReturn(rate);

        Rate actual = this.rateService.getFirstUpToDate();

        assertNotNull(actual);
        assertEquals(actual, rate);
    }

    @Test
    public void shouldGetFirstUpToDateWithParams() {
        Rate rate = mock(Rate.class);
        when(this.currencyService.get(anyString())).thenReturn(mock(Currency.class));
        when(this.rateDao.findTopByFirstCurrencyAndSecondCurrencyOrderByDateDesc(any(Currency.class), any(Currency.class))).thenReturn(rate);

        Rate actual = this.rateService.getFirstUpToDate("USD", "RUB");

        assertNotNull(actual);
        assertEquals(actual, rate);
    }


}
