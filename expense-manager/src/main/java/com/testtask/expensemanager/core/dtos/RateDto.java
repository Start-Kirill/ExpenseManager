package com.testtask.expensemanager.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testtask.expensemanager.core.enums.RateStatus;
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

    @JsonProperty(value = "first_currency_name")
    private String firstCurrencyName;

    @JsonProperty(value = "second_currency_name")
    private String secondCurrencyName;

    private BigDecimal value;

    @JsonProperty(value = "datetime")
    private LocalDateTime dateTime;

    private RateStatus status;

    @JsonProperty(value = "dt_create")
    private LocalDateTime dtCreate;

    @JsonProperty(value = "dt_update")
    private LocalDateTime dtUpdate;

    public RateDto(UUID uuid, String firstCurrencyName, String secondCurrencyName, BigDecimal value, LocalDateTime dateTime, RateStatus status, LocalDateTime dtCreate, LocalDateTime dtUpdate) {
        this.uuid = uuid;
        this.firstCurrencyName = firstCurrencyName;
        this.secondCurrencyName = secondCurrencyName;
        this.value = value;
        this.dateTime = dateTime;
        this.status = status;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateDto rateDto = (RateDto) o;
        return Objects.equals(uuid, rateDto.uuid) && Objects.equals(firstCurrencyName, rateDto.firstCurrencyName) && Objects.equals(secondCurrencyName, rateDto.secondCurrencyName) && Objects.equals(value, rateDto.value) && Objects.equals(dateTime, rateDto.dateTime) && status == rateDto.status && Objects.equals(dtCreate, rateDto.dtCreate) && Objects.equals(dtUpdate, rateDto.dtUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, firstCurrencyName, secondCurrencyName, value, dateTime, status, dtCreate, dtUpdate);
    }

    @Override
    public String toString() {
        return "RateDto{" +
                "uuid=" + uuid +
                ", firstCurrencyName='" + firstCurrencyName + '\'' +
                ", secondCurrencyName='" + secondCurrencyName + '\'' +
                ", value=" + value +
                ", dateTime=" + dateTime +
                ", status=" + status +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                '}';
    }
}
