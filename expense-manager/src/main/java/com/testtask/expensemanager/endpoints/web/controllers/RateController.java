package com.testtask.expensemanager.endpoints.web.controllers;

import com.testtask.expensemanager.core.dtos.RateDto;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.services.api.IRateService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/rates")
public class RateController {

    private final IRateService rateService;

    private final ConversionService conversionService;

    public RateController(IRateService rateService, ConversionService conversionService) {
        this.rateService = rateService;
        this.conversionService = conversionService;
    }

    @GetMapping
    public ResponseEntity<?> get() {
        List<Rate> rates = this.rateService.get();
        List<RateDto> rateDtos = rates.stream().map(r -> this.conversionService.convert(r, RateDto.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(rateDtos);
    }
}
