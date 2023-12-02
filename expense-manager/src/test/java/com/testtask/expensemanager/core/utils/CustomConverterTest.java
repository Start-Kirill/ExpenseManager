package com.testtask.expensemanager.core.utils;


import com.testtask.expensemanager.core.dtos.ExternalRateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateValueDto;
import com.testtask.expensemanager.core.dtos.RateCreateDto;
import com.testtask.expensemanager.core.enums.RateStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomConverterTest {

    @Test
    public void shouldConvert() {
        BigDecimal value = BigDecimal.valueOf(12.1);
        LocalDateTime now = LocalDateTime.now();
        List<RateCreateDto> rateCreateDtos = List.of(new RateCreateDto("USD", "RUB", value, now, RateStatus.CREATED));

        List<RateCreateDto> actual = CustomConverter.convert(create("USD/RUB", 12.1, now), RateStatus.CREATED);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual, rateCreateDtos);
    }

    private Map<String, ExternalRateDto> create(String pair, double value, LocalDateTime dateTime) {
        Map<String, ExternalRateDto> externalRates = new HashMap<>();
        ExternalRateDto externalRateDto = new ExternalRateDto();
        List<ExternalRateValueDto> externalRateValueDtos = List.of(new ExternalRateValueDto(dateTime, BigDecimal.valueOf(value)));
        externalRateDto.setValues(externalRateValueDtos);
        externalRates.put(pair, externalRateDto);

        return externalRates;
    }
}
