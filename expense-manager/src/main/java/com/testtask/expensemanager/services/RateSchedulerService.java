package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.ExternalRateCreateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateValueDto;
import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.dao.entyties.Currency;
import com.testtask.expensemanager.dao.entyties.Rate;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.IExternalRateService;
import com.testtask.expensemanager.services.api.IRateSchedulerService;
import com.testtask.expensemanager.services.api.IRateService;
import jakarta.annotation.PostConstruct;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RateSchedulerService implements IRateSchedulerService {

    private final ICurrencyService currencyService;

    private final IExternalRateService externalRateService;

    private final IRateService rateService;


    public RateSchedulerService(ICurrencyService currencyService,
                                IExternalRateService externalRateService,
                                IRateService rateService) {
        this.currencyService = currencyService;
        this.externalRateService = externalRateService;
        this.rateService = rateService;
    }

    @PostConstruct
    void init() {
        execute();
    }

    //    TODO Обработка ошибок. Превышении лимита запросов,  и т.п.
    @Scheduled(cron = "1 0 0 * * ?")
    @Override
    public void execute() {
        Rate firstUpToDate = this.rateService.getFirstUpToDate();

        if (firstUpToDate == null || !firstUpToDate.getDate().toLocalDate().equals(LocalDate.now())) {
            List<Currency> currencies = this.currencyService.get();

            ExternalRateCreateDto externalRateCreateDto = createForToday(currencies);

            Map<String, ExternalRateDto> externalRates = this.externalRateService.get(externalRateCreateDto);

            List<RateCreateDto> rateCreateDtos = convert(externalRates);

            this.rateService.saveAll(rateCreateDtos);
        }

    }

    private List<Pair<String, String>> createPairs(List<Currency> currencies) {
        List<Pair<String, String>> pairs = new ArrayList<>();

        int index = 0;
        int currentIndex = 0;

        while (index < currencies.size() - 1) {
            currentIndex = index + 1;
            while (currentIndex < currencies.size()) {
                Currency firstCurrency = currencies.get(index);
                Currency secondCurrency = currencies.get(currentIndex);
                pairs.add(Pair.of(firstCurrency.getName(), secondCurrency.getName()));
                pairs.add(Pair.of(secondCurrency.getName(), firstCurrency.getName()));
                currentIndex++;
            }
            index++;
        }

        return pairs;
    }

    private List<RateCreateDto> convert(Map<String, ExternalRateDto> externalRates) {
        List<RateCreateDto> rateCreateDtos = new ArrayList<>();
        for (Map.Entry<String, ExternalRateDto> entry : externalRates.entrySet()) {
            List<ExternalRateValueDto> externalRateValueDtos = entry.getValue().getValues();
            if (externalRateValueDtos != null) {
                String[] currencyNames = entry.getKey().split("/");
                String firstCurrencyName = currencyNames[0];
                String secondCurrencyName = currencyNames[1];
                externalRateValueDtos.forEach(ex -> rateCreateDtos.add(new RateCreateDto(firstCurrencyName, secondCurrencyName, ex.getValue(), ex.getDatetime())));
            }
        }
        return rateCreateDtos;
    }

    private ExternalRateCreateDto createForToday(List<Currency> currencies) {
        List<Pair<String, String>> pairs = createPairs(currencies);
        ExternalRateCreateDto externalRateCreateDto = new ExternalRateCreateDto();
        externalRateCreateDto.setCurrencyPairs(pairs);
        externalRateCreateDto.setStartDate(LocalDate.now());
        externalRateCreateDto.setEndDate(LocalDate.now().plusDays(1));
        return externalRateCreateDto;
    }
}
