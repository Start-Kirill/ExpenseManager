package com.testtask.expensemanager.services.api;

import com.testtask.expensemanager.core.dtos.ExternalRateCreateDto;
import com.testtask.expensemanager.core.dtos.ExternalRateDto;

import java.util.Map;

public interface IExternalRateService {

    Map<String, ExternalRateDto> get(ExternalRateCreateDto externalRateCreateDto);

}
