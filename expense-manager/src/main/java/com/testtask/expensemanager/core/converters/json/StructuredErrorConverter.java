package com.testtask.expensemanager.core.converters.json;



import com.fasterxml.jackson.databind.util.StdConverter;
import com.testtask.expensemanager.core.errors.SpecificError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StructuredErrorConverter extends StdConverter<Map<String, String>, List<SpecificError>> {
    @Override
    public List<SpecificError> convert(Map<String, String> value) {
        List<SpecificError> errors = new ArrayList<>();
        value.forEach((f, m) -> errors.add(new SpecificError(f, m)));
        return errors;
    }
}
