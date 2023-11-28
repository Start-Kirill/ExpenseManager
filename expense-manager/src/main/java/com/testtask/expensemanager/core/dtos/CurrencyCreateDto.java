package com.testtask.expensemanager.core.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CurrencyCreateDto {

    private String name;

    public CurrencyCreateDto() {
    }

    public CurrencyCreateDto(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyCreateDto that = (CurrencyCreateDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CurrencyCreateDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
