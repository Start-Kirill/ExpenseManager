package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.core.enums.RateStatus;
import com.testtask.expensemanager.dao.api.IRateDao;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.IExternalRateService;
import com.testtask.expensemanager.services.exceptions.FailedSaveRateException;
import com.testtask.expensemanager.services.exceptions.RateDateTimeUpdatesNotMatchException;
import com.testtask.expensemanager.services.exceptions.SuchRateNotExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    public void shouldUpdate() {
        Rate rate = mock(Rate.class);
        when(rate.getUuid()).thenReturn(mock(UUID.class));
        LocalDateTime now = LocalDateTime.now();
        when(rate.getDtUpdate()).thenReturn(now);
        Rate secondRate = mock(Rate.class);
        when(secondRate.getDtUpdate()).thenReturn(now);
        when(this.rateDao.findById(any(UUID.class))).thenReturn(Optional.of(secondRate));
        when(this.rateDao.saveAndFlush(any(Rate.class))).thenReturn(rate);

        Rate actual = this.rateService.update(rate);

        assertNotNull(actual);
        assertEquals(rate, actual);

    }

    @Test
    public void shouldThrowWhileUpdate() {
        Rate rate = mock(Rate.class);
        when(rate.getUuid()).thenReturn(mock(UUID.class));
        LocalDateTime now = LocalDateTime.now();
        when(rate.getDtUpdate()).thenReturn(now);
        Rate secondRate = mock(Rate.class);
        when(secondRate.getDtUpdate()).thenReturn(LocalDateTime.now());
        when(this.rateDao.findById(any(UUID.class))).thenReturn(Optional.of(secondRate));


        assertThrows(RateDateTimeUpdatesNotMatchException.class, () -> this.rateService.update(rate));

    }

    @Test
    public void shouldUpdateAll() {
        Rate rate = mock(Rate.class);
        List<Rate> rates = List.of(rate);
        when(rate.getUuid()).thenReturn(mock(UUID.class));
        LocalDateTime now = LocalDateTime.now();
        when(rate.getDtUpdate()).thenReturn(now);
        Rate secondRate = mock(Rate.class);
        when(secondRate.getDtUpdate()).thenReturn(now);
        when(this.rateDao.findById(any(UUID.class))).thenReturn(Optional.of(secondRate));
        when(this.rateDao.saveAndFlush(any(Rate.class))).thenReturn(rate);

        List<Rate> actual = this.rateService.updateAll(rates);

        assertNotNull(actual);
        assertEquals(rates, actual);

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
        when(this.rateDao.findTopByOrderByDatetimeDesc()).thenReturn(rate);

        Rate actual = this.rateService.getFirstUpToDate();

        assertNotNull(actual);
        assertEquals(actual, rate);
    }

    @Test
    public void shouldGetFirstUpToDateWithParams() {
        Rate rate = mock(Rate.class);
        when(this.currencyService.get(anyString())).thenReturn(mock(Currency.class));
        when(this.rateDao.findTopByFirstCurrencyAndSecondCurrencyAndStatusOrderByDatetimeDesc(any(Currency.class), any(Currency.class), any(RateStatus.class))).thenReturn(rate);

        Rate actual = this.rateService.getFirstUpToDate("USD", "RUB");

        assertNotNull(actual);
        assertEquals(actual, rate);
    }


}
