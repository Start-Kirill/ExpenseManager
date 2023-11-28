package com.testtask.expensemanager.core.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class RateCreateDto {

    private UUID firstCurrencyUuid;

    private UUID secondCurrencyUuid;

    private BigDecimal value;

    private LocalDateTime dateTime;

    public RateCreateDto() {
    }

    public RateCreateDto(UUID firstCurrencyUuid, UUID secondCurrencyUuid, BigDecimal value, LocalDateTime dateTime) {
        this.firstCurrencyUuid = firstCurrencyUuid;
        this.secondCurrencyUuid = secondCurrencyUuid;
        this.value = value;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateCreateDto that = (RateCreateDto) o;
        return Objects.equals(firstCurrencyUuid, that.firstCurrencyUuid) && Objects.equals(secondCurrencyUuid, that.secondCurrencyUuid) && Objects.equals(value, that.value) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstCurrencyUuid, secondCurrencyUuid, value, dateTime);
    }

    @Override
    public String toString() {
        return "RateCreateDto{" +
                "firstCurrencyUuid=" + firstCurrencyUuid +
                ", secondCurrencyUuid=" + secondCurrencyUuid +
                ", value=" + value +
                ", dateTime=" + dateTime +
                '}';
    }
}
