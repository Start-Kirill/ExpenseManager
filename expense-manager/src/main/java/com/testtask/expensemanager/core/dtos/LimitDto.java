package com.testtask.expensemanager.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testtask.expensemanager.core.enums.ExpenseCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class LimitDto {

    private UUID uuid;

    @JsonProperty("datetime_create")
    private LocalDateTime dateTimeCreate;

    @JsonProperty("expense_category")
    private ExpenseCategory expenseCategory;

    @JsonProperty("limit_sum")
    private BigDecimal limitSum;


    @JsonProperty("currency")
    private String currencyName;

    public LimitDto() {
    }

    public LimitDto(UUID uuid, LocalDateTime dateTimeCreate, ExpenseCategory expenseCategory, BigDecimal limitSum, String currencyName) {
        this.uuid = uuid;
        this.dateTimeCreate = dateTimeCreate;
        this.expenseCategory = expenseCategory;
        this.limitSum = limitSum;
        this.currencyName = currencyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LimitDto limitDto = (LimitDto) o;
        return Objects.equals(uuid, limitDto.uuid) && Objects.equals(dateTimeCreate, limitDto.dateTimeCreate) && expenseCategory == limitDto.expenseCategory && Objects.equals(limitSum, limitDto.limitSum) && Objects.equals(currencyName, limitDto.currencyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dateTimeCreate, expenseCategory, limitSum, currencyName);
    }

    @Override
    public String toString() {
        return "LimitDto{" +
                "uuid=" + uuid +
                ", dateTimeCreate=" + dateTimeCreate +
                ", expenseCategory=" + expenseCategory +
                ", limitSum=" + limitSum +
                ", currencyName='" + currencyName + '\'' +
                '}';
    }
}
