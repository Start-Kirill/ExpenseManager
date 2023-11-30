package com.testtask.expensemanager.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RateDto {

    private UUID uuid;

    @JsonProperty(namespace = "first_currency_name")
    private String firstCurrencyName;

    @JsonProperty(namespace = "second_currency_name")
    private String secondCurrencyName;

    private BigDecimal value;

    @JsonProperty(namespace = "datetime")
    private LocalDateTime dateTime;

    public RateDto(UUID uuid, String firstCurrencyName, String secondCurrencyName, BigDecimal value, LocalDateTime dateTime) {
        this.uuid = uuid;
        this.firstCurrencyName = firstCurrencyName;
        this.secondCurrencyName = secondCurrencyName;
        this.value = value;
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateDto rateDto = (RateDto) o;
        return Objects.equals(uuid, rateDto.uuid) && Objects.equals(firstCurrencyName, rateDto.firstCurrencyName) && Objects.equals(secondCurrencyName, rateDto.secondCurrencyName) && Objects.equals(value, rateDto.value) && Objects.equals(dateTime, rateDto.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, firstCurrencyName, secondCurrencyName, value, dateTime);
    }

    @Override
    public String toString() {
        return "RateDto{" +
                "uuid=" + uuid +
                ", firstCurrencyName='" + firstCurrencyName + '\'' +
                ", secondCurrencyName='" + secondCurrencyName + '\'' +
                ", value=" + value +
                ", dateTime=" + dateTime +
                '}';
    }
}
