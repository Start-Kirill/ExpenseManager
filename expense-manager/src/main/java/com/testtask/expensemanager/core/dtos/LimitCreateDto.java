package com.testtask.expensemanager.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.testtask.expensemanager.core.converters.json.StringToBigDecimalWithRoundingConverter;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class LimitCreateDto {

    @JsonDeserialize(converter = StringToBigDecimalWithRoundingConverter.class)
    @JsonProperty(value = "limit_sum")
    private BigDecimal limitSum;

    @JsonProperty(value = "expense_category")
    private ExpenseCategory expenseCategory;


    public LimitCreateDto(BigDecimal limitSum, ExpenseCategory expenseCategory) {
        this.limitSum = limitSum;
        this.expenseCategory = expenseCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LimitCreateDto that = (LimitCreateDto) o;
        return Objects.equals(limitSum, that.limitSum) && expenseCategory == that.expenseCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(limitSum, expenseCategory);
    }

    @Override
    public String toString() {
        return "LimitCreateDto{" +
                "limitSum=" + limitSum +
                ", expenseCategory=" + expenseCategory +
                '}';
    }
}
