package com.testtask.expensemanager.core.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ExternalRateMetaDto {

    private String symbol;

    private String interval;

    public ExternalRateMetaDto(String symbol, String interval) {
        this.symbol = symbol;
        this.interval = interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExternalRateMetaDto that = (ExternalRateMetaDto) o;
        return Objects.equals(symbol, that.symbol) && Objects.equals(interval, that.interval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, interval);
    }

    @Override
    public String toString() {
        return "ExternalRateMetaDto{" +
                "symbol='" + symbol + '\'' +
                ", interval='" + interval + '\'' +
                '}';
    }
}
