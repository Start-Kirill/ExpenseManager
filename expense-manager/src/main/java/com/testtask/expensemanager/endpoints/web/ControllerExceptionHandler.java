package com.testtask.expensemanager.endpoints.web;

import com.testtask.expensemanager.core.enums.ErrorType;
import com.testtask.expensemanager.core.errors.ErrorResponse;
import com.testtask.expensemanager.core.errors.StructuredErrorResponse;
import com.testtask.expensemanager.core.exceptions.common.CommonErrorException;
import com.testtask.expensemanager.core.exceptions.common.CommonInternalErrorException;
import com.testtask.expensemanager.core.exceptions.common.StructuredErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final String EXCEED_PARAM_NAME = "exceeded_only";

    private static final String INCORRECT_DATA_OR_NOT_ENOUGH_MESSAGE = "Passed data is incorrect or not enough";

    private static final String SERVER_INTERNAL_ERROR_MESSAGE = "The server was unable to process the request correctly. Please contact administrator";

    private static final String INVALID_EXCEED_MESSAGE = "Invalid exceeded_only value. Change the request and repeat";

    private static final String REQUEST_INVALID_DATA_MESSAGE = "The request contains invalid data. Change the request and send it again";

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        List<ErrorResponse> error = new ArrayList<>();
        Map<String, String> mapErrors = new HashMap<>();
        if (EXCEED_PARAM_NAME.equals(ex.getPropertyName())) {
            mapErrors.put(EXCEED_PARAM_NAME, INVALID_EXCEED_MESSAGE);
        } else {
            error.add(new ErrorResponse(ErrorType.ERROR, REQUEST_INVALID_DATA_MESSAGE));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(mapErrors.isEmpty() ? error : new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, mapErrors));
    }

    @ExceptionHandler(StructuredErrorException.class)
    public ResponseEntity<?> handlerStructuredErrorException(StructuredErrorException ex) {
        Map<String, String> errors = ex.getErrors();
        StructuredErrorResponse structuredErrorResponse = new StructuredErrorResponse(ErrorType.STRUCTURED_ERROR, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(structuredErrorResponse);
    }

    @ExceptionHandler(CommonErrorException.class)
    public ResponseEntity<?> handlerCommonErrorException(CommonErrorException ex) {
        List<ErrorResponse> errors = ex.getErrors();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(CommonInternalErrorException.class)
    public ResponseEntity<?> handlerCommonInternalErrorException(CommonInternalErrorException ex) {
        List<ErrorResponse> errors = ex.getErrors();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handlerHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        List<ErrorResponse> errors = new ArrayList<>();
        errors.add(new ErrorResponse(ErrorType.ERROR, INCORRECT_DATA_OR_NOT_ENOUGH_MESSAGE));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handlerNullPointerException(NullPointerException ex) {
        List<ErrorResponse> errors = new ArrayList<>();
        errors.add(new ErrorResponse(ErrorType.ERROR, SERVER_INTERNAL_ERROR_MESSAGE));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }
}
