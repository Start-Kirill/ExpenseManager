package com.testtask.expensemanager.endpoints.web.controllers;

import com.testtask.expensemanager.core.dtos.TransactionCreateDto;
import com.testtask.expensemanager.core.dtos.TransactionDto;
import com.testtask.expensemanager.dao.entyties.Transaction;
import com.testtask.expensemanager.services.api.ITransactionService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final ITransactionService transactionService;

    private final ConversionService conversionService;

    public TransactionController(ITransactionService transactionService,
                                 ConversionService conversionService) {
        this.transactionService = transactionService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public ResponseEntity<?> commit(@RequestBody TransactionCreateDto transactionCreateDto) {
        Transaction transaction = this.transactionService.save(transactionCreateDto);
        TransactionDto transactionDto = this.conversionService.convert(transaction, TransactionDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDto);
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam(required = false, defaultValue = "false", name = "exceeded_only") boolean exceededOnly) {
        List<Transaction> transactions = exceededOnly ? this.transactionService.getExceeded() : this.transactionService.get();
        List<TransactionDto> transactionDtos = transactions.stream().map(t -> this.conversionService.convert(t, TransactionDto.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(transactionDtos);
    }

}
