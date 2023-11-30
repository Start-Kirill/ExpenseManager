package com.testtask.expensemanager.core.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ExternalRateDto {

    private ExternalRateMetaDto meta;

    private List<ExternalRateValueDto> values;

    private String status;

    private Integer code;

    public ExternalRateDto(ExternalRateMetaDto meta, List<ExternalRateValueDto> values, String status, Integer code) {
        this.meta = meta;
        this.values = values;
        this.status = status;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExternalRateDto that = (ExternalRateDto) o;
        return Objects.equals(meta, that.meta) && Objects.equals(values, that.values) && Objects.equals(status, that.status) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meta, values, status, code);
    }

    @Override
    public String
    toString() {
        return "ExternalRateDto{" +
                "meta=" + meta +
                ", values=" + values +
                ", status='" + status + '\'' +
                ", code=" + code +
                '}';
    }
}
