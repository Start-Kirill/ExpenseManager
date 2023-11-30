package com.testtask.expensemanager.core.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.testtask.expensemanager.core.converters.json.StringToBigDecimalConverter;
import com.testtask.expensemanager.core.converters.json.StringToLocalDateTimeConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ExternalRateValueDto {

    @JsonDeserialize(converter = StringToLocalDateTimeConverter.class)
    private LocalDateTime datetime;

    @JsonDeserialize(converter = StringToBigDecimalConverter.class)
    @JsonProperty(value = "close")
    private BigDecimal value;

    public ExternalRateValueDto(LocalDateTime datetime, BigDecimal value) {
        this.datetime = datetime;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExternalRateValueDto that = (ExternalRateValueDto) o;
        return Objects.equals(datetime, that.datetime) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datetime, value);
    }

    @Override
    public String toString() {
        return "ExternalRateValueDto{" +
                "datetime=" + datetime +
                ", value=" + value +
                '}';
    }
}
