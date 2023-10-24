package com.eduardo.picpaysimplificado.controller;

import com.eduardo.picpaysimplificado.domain.transaction.Transaction;
import com.eduardo.picpaysimplificado.domain.transaction.TransactionDTO;
import com.eduardo.picpaysimplificado.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        var newTransaction = this.service.createTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}
