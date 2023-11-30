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
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ControllerExceptionHandler {

    private static final String INCORRECT_DATA_OR_NOT_ENOUGH_MESSAGE = "Passed data is incorrect or not enough";

    private static final String SERVER_INTERNAL_ERROR_MESSAGE = "The server was unable to process the request correctly. Please contact administrator";

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
