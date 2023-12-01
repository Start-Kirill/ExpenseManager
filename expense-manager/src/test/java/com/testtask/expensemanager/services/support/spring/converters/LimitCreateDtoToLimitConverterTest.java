package com.testtask.expensemanager.services.support.spring.converters;

import com.testtask.expensemanager.core.dtos.LimitCreateDto;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import com.testtask.expensemanager.dao.entyties.Limit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class LimitCreateDtoToLimitConverterTest {

    @InjectMocks
    private LimitCreateDtoToLimitConverter converter;

    @Test
    public void shouldConvert() {
        BigDecimal bigDecimal = mock(BigDecimal.class);

        LimitCreateDto limitCreateDto = new LimitCreateDto(bigDecimal, ExpenseCategory.PRODUCT);

        Limit limit = new Limit();
        limit.setLimitSum(bigDecimal);
        limit.setExpenseCategory(ExpenseCategory.PRODUCT);

        Limit actual = this.converter.convert(limitCreateDto);

        assertNotNull(actual);
        assertEquals(actual, limit);
    }
}
