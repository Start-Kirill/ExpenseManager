package com.testtask.expensemanager.endpoints.web.controllers;

import com.testtask.expensemanager.core.dtos.CurrencyCreateDto;
import com.testtask.expensemanager.core.dtos.CurrencyDto;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.services.api.ICurrencyService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/currencies")
public class CurrencyController {

    private final ICurrencyService currencyService;

    private final ConversionService conversionService;

    public CurrencyController(ICurrencyService currencyService, ConversionService conversionService) {
        this.currencyService = currencyService;
        this.conversionService = conversionService;
    }

    @GetMapping
    public ResponseEntity<?> get() {
        List<Currency> currencies = this.currencyService.get();
        List<CurrencyDto> currencyDtos = currencies.stream().map(c -> this.conversionService.convert(c, CurrencyDto.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(currencyDtos);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CurrencyCreateDto currencyCreateDto) {
        Currency save = this.currencyService.save(currencyCreateDto);
        CurrencyDto currencyDto = this.conversionService.convert(save, CurrencyDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(currencyDto);
    }

}
