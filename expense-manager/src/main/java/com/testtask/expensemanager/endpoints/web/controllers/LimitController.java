package com.testtask.expensemanager.endpoints.web.controllers;

import com.testtask.expensemanager.core.dtos.LimitCreateDto;
import com.testtask.expensemanager.core.dtos.LimitDto;
import com.testtask.expensemanager.dao.entyties.Limit;
import com.testtask.expensemanager.services.api.ILimitService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/limits")
public class LimitController {

    private final ILimitService limitService;

    private final ConversionService conversionService;

    public LimitController(ILimitService limitService,
                           ConversionService conversionService) {
        this.limitService = limitService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LimitCreateDto limitCreateDto) {
        Limit limit = this.limitService.save(limitCreateDto);
        LimitDto limitDto = this.conversionService.convert(limit, LimitDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(limitDto);
    }

}
