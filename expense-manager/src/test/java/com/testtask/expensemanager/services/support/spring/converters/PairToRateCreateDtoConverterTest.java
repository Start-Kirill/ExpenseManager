package com.testtask.expensemanager.services.support.spring.converters;

import com.testtask.expensemanager.core.dtos.RateCreateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

@ExtendWith(MockitoExtension.class)
public class PairToRateCreateDtoConverterTest {

    @InjectMocks
    private PairToRateCreateDtoConverter converter;

    @Test
    public void shouldConvert() {
        Pair<String, String> stringStringPair = Pair.of("USD", "RUB");
        RateCreateDto rateCreateDto = new RateCreateDto();
        rateCreateDto.setSecondCurrencyName("RUB");
        rateCreateDto.setFirstCurrencyName("USD");


        RateCreateDto actual = this.converter.convert(stringStringPair);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual, rateCreateDto);
    }
}
