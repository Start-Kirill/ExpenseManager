package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.CurrencyCreateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateDto;
import com.testtask.expensemanager.dao.api.ICurrencyDao;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.services.api.IExternalRateService;
import com.testtask.expensemanager.services.api.IRateService;
import com.testtask.expensemanager.services.exceptions.InvalidCurrencyBodyException;
import com.testtask.expensemanager.services.exceptions.SuchCurrencyNotExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTest {

    @InjectMocks
    private CurrencyService currencyService;

    @Mock
    private ICurrencyDao currencyDao;

    @Mock
    private IExternalRateService externalRateService;

    @Mock
    private IRateService rateService;

    @Mock
    private ConversionService conversionService;

    @Test
    public void shouldSave() {
        Currency currency = mock(Currency.class);
        when(this.conversionService.convert(any(CurrencyCreateDto.class), eq(Currency.class))).thenReturn(currency);
        when(this.currencyDao.save(any(Currency.class))).thenReturn(currency);
        ExternalRateDto externalRateDto = mock(ExternalRateDto.class);
        when(externalRateDto.getStatus()).thenReturn("ok");
        HashMap<String, ExternalRateDto> lastThirty = new HashMap<>();
        lastThirty.put("Test/Test", externalRateDto);
        when(this.externalRateService.getLastThirty(any(List.class))).thenReturn(lastThirty);

        CurrencyCreateDto usd = new CurrencyCreateDto("USD");
        Currency actual = this.currencyService.save(usd);

        assertNotNull(actual);
        assertEquals(actual, currency);
    }

    @Test
    public void shouldThrowWhileSave() {
        ExternalRateDto externalRateDto = mock(ExternalRateDto.class);
        when(externalRateDto.getStatus()).thenReturn("error");
        HashMap<String, ExternalRateDto> lastThirty = new HashMap<>();
        lastThirty.put("Test/Test", externalRateDto);
        when(this.externalRateService.getLastThirty(any(List.class))).thenReturn(lastThirty);

        CurrencyCreateDto usd = new CurrencyCreateDto("USD");

        assertThrows(InvalidCurrencyBodyException.class, () -> this.currencyService.save(usd));
    }

    @Test
    public void shouldGetByUuid() {
        Currency currency = mock(Currency.class);
        when(this.currencyDao.findById(any(UUID.class))).thenReturn(Optional.of(currency));

        Currency actual = this.currencyService.get(mock(UUID.class));

        assertNotNull(actual);
        assertEquals(actual, currency);
    }

    @Test
    public void shouldThrowWhileGetByUuid() {
        when(this.currencyDao.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(SuchCurrencyNotExistsException.class, () -> this.currencyService.get(mock(UUID.class)));
    }

    @Test
    public void shouldGetByName() {
        Currency currency = mock(Currency.class);
        when(this.currencyDao.findByName(anyString())).thenReturn(Optional.of(currency));

        Currency actual = this.currencyService.get(anyString());

        assertNotNull(actual);
        assertEquals(actual, currency);
    }

    @Test
    public void shouldThrowWhileGetByName() {
        when(this.currencyDao.findByName(anyString())).thenReturn(Optional.empty());

        assertThrows(SuchCurrencyNotExistsException.class, () -> this.currencyService.get(anyString()));
    }

    @Test
    public void shouldGet() {
        List<Currency> currencies = new ArrayList<>();
        when(this.currencyDao.findAll()).thenReturn(currencies);

        List<Currency> actual = this.currencyService.get();

        assertNotNull(actual);
        assertEquals(actual, currencies);
    }

}
