package com.abcbank.abcmorgage.controller;

import com.abcbank.abcmorgage.dto.TransactionResponse;
import com.abcbank.abcmorgage.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/summary/{accountNumber}")
    public ResponseEntity<List<TransactionResponse>> getTransactionSummary(@PathVariable Long accountNumber) {
        List<TransactionResponse> transactions = transactionService.getTransactionSummary(accountNumber);
        return ResponseEntity.ok(transactions);
    }


}
