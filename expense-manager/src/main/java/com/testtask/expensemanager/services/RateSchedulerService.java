package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.ExternalRateCreateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateValueDto;
import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.core.enums.RateStatus;
import com.testtask.expensemanager.core.utils.CustomConverter;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.IExternalRateService;
import com.testtask.expensemanager.services.api.IRateSchedulerService;
import com.testtask.expensemanager.services.api.IRateService;
import com.testtask.expensemanager.services.exceptions.FailedRateAccessException;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class RateSchedulerService implements IRateSchedulerService {

    private final String STATUS_OK_MESSAGE = "ok";

    private final ICurrencyService currencyService;

    private final IExternalRateService externalRateService;

    private final IRateService rateService;

    private final ConversionService conversionService;


    public RateSchedulerService(ICurrencyService currencyService,
                                IExternalRateService externalRateService,
                                IRateService rateService,
                                ConversionService conversionService) {
        this.currencyService = currencyService;
        this.externalRateService = externalRateService;
        this.rateService = rateService;
        this.conversionService = conversionService;
    }


    @Scheduled(fixedDelay = 70000)
    @Override
    public void execute() {

        if (this.rateService.isEmpty()) {
            List<Pair<String, String>> pairs = this.currencyService.getCurrencyPairs();

            Map<String, ExternalRateDto> lastThirty = this.externalRateService.getLastThirty(pairs);

            List<RateCreateDto> rateCreateDtos = CustomConverter.convert(lastThirty, RateStatus.DONE);

            this.rateService.saveAll(rateCreateDtos);
        } else {
            List<Rate> createdRates = this.rateService.getByStatus(RateStatus.CREATED);
            if (createdRates != null && !createdRates.isEmpty()) {
                ExternalRateCreateDto externalRateCreateDto = this.conversionService.convert(createdRates, ExternalRateCreateDto.class);
                List<Rate> filledRateChanges = null;
                try {
                    Map<String, ExternalRateDto> externalRateDtoMap = this.externalRateService.get(externalRateCreateDto);
                    filledRateChanges = fillRateChanges(createdRates, externalRateDtoMap);
                } catch (FailedRateAccessException ex) {
                    filledRateChanges = fillRateChangesInCaseError(createdRates);
                } finally {
                    this.rateService.updateAll(filledRateChanges);
                }
            }
        }
    }


    private List<Rate> fillRateChanges(List<Rate> rates, Map<String, ExternalRateDto> externalRates) {
        Map<Integer, Rate> integerRateMap = convert(rates);
        externalRates.forEach((key, value) -> {
            String status = value.getStatus();
            if (STATUS_OK_MESSAGE.equalsIgnoreCase(status)) {
                String[] currencies = key.split("/");
                String firstCurrency = currencies[0];
                String secondCurrency = currencies[1];
                List<ExternalRateValueDto> externalRateValueDtos = value.getValues();
                if (externalRateValueDtos != null) {
                    externalRateValueDtos.forEach(v -> {
                        LocalDate dateTime = v.getDatetime().toLocalDate();
                        int hash = Objects.hash(firstCurrency, secondCurrency, dateTime);
                        if (integerRateMap.containsKey(hash)) {
                            Rate rate = integerRateMap.get(hash);
                            rate.setValue(v.getValue());
                            rate.setStatus(RateStatus.DONE);
                        }
                    });
                }
            }
        });
        rates.forEach(r -> {
            if (r.getValue() == null) {
                if (r.getAttempt() < 5) {
                    r.setAttempt(r.getAttempt() + 1);
                } else {
                    r.setStatus(RateStatus.ERROR);
                }
            }
        });
        return rates;
    }

    private Map<Integer, Rate> convert(List<Rate> rates) {
        Map<Integer, Rate> integerRateHashMap = new HashMap<>();
        rates.forEach(r -> {
            int hash = Objects.hash(r.getFirstCurrency().getName(), r.getSecondCurrency().getName(), r.getDatetime().toLocalDate());
            integerRateHashMap.put(hash, r);
        });
        return integerRateHashMap;
    }


    private List<Rate> fillRateChangesInCaseError(List<Rate> rates) {
        rates.forEach(r -> {
            Long attempt = r.getAttempt();
            if (attempt < 5) {
                r.setAttempt(attempt + 1);
            } else {
                r.setStatus(RateStatus.ERROR);
            }
        });
        return rates;
    }
}
