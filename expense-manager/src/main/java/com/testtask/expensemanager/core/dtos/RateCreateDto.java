package com.testtask.expensemanager.core.dtos;

import com.testtask.expensemanager.core.enums.RateStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class RateCreateDto {

    private String firstCurrencyName;

    private String secondCurrencyName;

    private BigDecimal value;

    private LocalDateTime dateTime;

    private RateStatus status;

    public RateCreateDto(String firstCurrencyName, String secondCurrencyName, BigDecimal value, LocalDateTime dateTime, RateStatus status) {
        this.firstCurrencyName = firstCurrencyName;
        this.secondCurrencyName = secondCurrencyName;
        this.value = value;
        this.dateTime = dateTime;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateCreateDto that = (RateCreateDto) o;
        return Objects.equals(firstCurrencyName, that.firstCurrencyName) && Objects.equals(secondCurrencyName, that.secondCurrencyName) && Objects.equals(value, that.value) && Objects.equals(dateTime, that.dateTime) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstCurrencyName, secondCurrencyName, value, dateTime, status);
    }

    @Override
    public String toString() {
        return "RateCreateDto{" +
                "firstCurrencyName='" + firstCurrencyName + '\'' +
                ", secondCurrencyName='" + secondCurrencyName + '\'' +
                ", value=" + value +
                ", dateTime=" + dateTime +
                ", status=" + status +
                '}';
    }
}
