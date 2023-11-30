package com.testtask.expensemanager.services.api;

import com.testtask.expensemanager.core.dtos.ExternalRateCreateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateDto;
import org.springframework.data.util.Pair;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IExternalRateService {

    Map<String, ExternalRateDto> get(ExternalRateCreateDto externalRateCreateDto);

    Map<String, ExternalRateDto> getLastThirty(List<Pair<String, String>> currencyPairs);


}
