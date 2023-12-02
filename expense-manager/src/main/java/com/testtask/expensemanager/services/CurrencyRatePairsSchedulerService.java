package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.core.enums.RateStatus;
import com.testtask.expensemanager.services.api.ICurrencyRatePairsSchedulerService;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.IRateService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CurrencyRatePairsSchedulerService implements ICurrencyRatePairsSchedulerService {


    private final ICurrencyService currencyService;


    private final IRateService rateService;


    private final ConversionService conversionService;


    public CurrencyRatePairsSchedulerService(ICurrencyService currencyService,
                                             IRateService rateService,
                                             ConversionService conversionService) {
        this.currencyService = currencyService;
        this.rateService = rateService;
        this.conversionService = conversionService;
    }


    @Scheduled(cron = "1 0 0 * * ?")
    @Override
    public void execute() {
        List<Pair<String, String>> currencyPairs = this.currencyService.getCurrencyPairs();
        List<RateCreateDto> rateCreateDtos = currencyPairs.stream().map(p -> {
            RateCreateDto rateCreateDto = this.conversionService.convert(p, RateCreateDto.class);
            rateCreateDto.setDateTime(LocalDateTime.now());
            rateCreateDto.setStatus(RateStatus.CREATED);
            return rateCreateDto;
        }).toList();
        this.rateService.saveAll(rateCreateDtos);
    }
}
