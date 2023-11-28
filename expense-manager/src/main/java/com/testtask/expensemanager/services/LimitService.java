package com.testtask.expensemanager.services;

import com.testtask.expensemanager.core.dtos.LimitCreateDto;
import com.testtask.expensemanager.dao.api.ILimitDao;
import com.testtask.expensemanager.dao.entyties.Limit;
import com.testtask.expensemanager.services.api.ICurrencyService;
import com.testtask.expensemanager.services.api.ILimitService;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LimitService implements ILimitService {

    private static final String LIMIT_CURRENCY_NAME = "USD";

    private final ILimitDao limitDao;

    private final ConversionService conversionService;

    private final ICurrencyService currencyService;

    public LimitService(ILimitDao limitDao,
                        ConversionService conversionService,
                        ICurrencyService currencyService) {
        this.limitDao = limitDao;
        this.conversionService = conversionService;
        this.currencyService = currencyService;
    }

    @Transactional(readOnly = true)
    @Override
    public Limit get(UUID uuid) {
        try {
            return this.limitDao.findById(uuid).orElseThrow();
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Limit> get() {
        return this.limitDao.findAll();
    }

    @Transactional
    @Override
    public Limit save(LimitCreateDto limitCreateDto) {

        validate(limitCreateDto);

        Limit limit = this.conversionService.convert(limitCreateDto, Limit.class);
        limit.setCurrency(this.currencyService.get(LIMIT_CURRENCY_NAME));
        limit.setDateTimeCreate(LocalDateTime.now());
        limit.setUuid(UUID.randomUUID());

        try {
            return this.limitDao.save(limit);
        } catch (Exception ex) {
            throw new RuntimeException();
        }

    }

    //    TODO
    @Override
    public BigDecimal getReminder(UUID uuid) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Limit getUpToDate() {
        try {
            return this.limitDao.findTopByOrderByDateTimeCreateDesc().orElseThrow();
//            TODO
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

//    TODO
    private void validate(LimitCreateDto limitCreateDto) {
    }

}
