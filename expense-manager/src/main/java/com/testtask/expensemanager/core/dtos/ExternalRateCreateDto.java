package com.testtask.expensemanager.core.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ExternalRateCreateDto {

    private List<Pair<String, String>> currencyPairs;

    private LocalDate startDate;

    private LocalDate endDate;

    public ExternalRateCreateDto(List<Pair<String, String>> currencyPairs, LocalDate startDate, LocalDate endDate) {
        this.currencyPairs = currencyPairs;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExternalRateCreateDto that = (ExternalRateCreateDto) o;
        return Objects.equals(currencyPairs, that.currencyPairs) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyPairs, startDate, endDate);
    }

    @Override
    public String toString() {
        return "ExternalRateCreateDto{" +
                "currencyPairs=" + currencyPairs +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
