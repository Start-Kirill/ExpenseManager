package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.LimitCreateDto;
import com.testtask.expensemanager.dao.api.ILimitDao;
import com.testtask.expensemanager.dao.entyties.Limit;
import com.testtask.expensemanager.services.api.ILimitService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimitService implements ILimitService {

    private final ILimitDao limitDao;

    private final ConversionService conversionService;

    public LimitService(ILimitDao limitDao,
                        ConversionService conversionService) {
        this.limitDao = limitDao;
        this.conversionService = conversionService;
    }

    @Override
    public List<Limit> get() {
        return null;
    }

    @Override
    public Limit save(LimitCreateDto limitCreateDto) {
        return null;
    }
}
