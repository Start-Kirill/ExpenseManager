package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.ExternalRateCreateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateDto;
import com.testtask.expensemanager.dao.entyties.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@SpringBootTest
public class ExternalRateServiceTest {

    @Autowired
    private ExternalRateService externalRateService;

    @Test
    public void testGetRates() {
        ExternalRateCreateDto externalRateCreateDto = new ExternalRateCreateDto();
        externalRateCreateDto.setCurrencyPairs(createPairs(getCurrencies()));
        externalRateCreateDto.setStartDate(LocalDate.of(2023, 11, 10));
        externalRateCreateDto.setEndDate(LocalDate.of(2023, 11, 30));

        Map<String, ExternalRateDto> stringExternalRateDtoMap = this.externalRateService.get(externalRateCreateDto);
        System.out.println(stringExternalRateDtoMap);

    }

    private List<String> getCurrenciesPairs() {
        List<String> pairs = new ArrayList<>();

        pairs.add("USD/RUB");
        pairs.add("RUB/USD");
        pairs.add("USD/BYN");
        pairs.add("BYN/USD");

        return pairs;
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

    private List<Currency> getCurrencies() {
        List<Currency> currencies = new ArrayList<>();

        currencies.add(new Currency(UUID.randomUUID(), "USD"));
        currencies.add(new Currency(UUID.randomUUID(), "RUB"));
        currencies.add(new Currency(UUID.randomUUID(), "BYN"));
//        currencies.add(new Currency(UUID.randomUUID(), "KZT"));
//        currencies.add(new Currency(UUID.randomUUID(), "EUR"));


        return currencies;
    }


}
