package com.abcbank.abcmorgage.service;

import com.abcbank.abcmorgage.dto.Transaction;
import com.abcbank.abcmorgage.dto.TransactionResponse;
import com.abcbank.abcmorgage.exception.AccountNotFoundException;
import com.abcbank.abcmorgage.exception.InvalidInputException;
import com.abcbank.abcmorgage.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionResponse> getTransactionSummary(Long accountNumber) {
        // Validate account number input
        if (accountNumber == null || accountNumber <= 0) {
            throw new InvalidInputException("Invalid account number provided");
        }

        // Fetch transactions for the account number (both incoming and outgoing)
        List<Transaction> transactions = transactionRepository.findByFromAccountNumberOrToAccountNumber(accountNumber, accountNumber);

        // If no transactions found, throw AccountNotFoundException
        if (transactions.isEmpty()) {
            throw new AccountNotFoundException("Customer to account not found");
        }

        // Map the transaction entities to response DTOs
        return transactions.stream()
                .map(this::mapToTransactionResponse)
                .collect(Collectors.toList());
    }

    private TransactionResponse mapToTransactionResponse(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setFromAccountNumber(transaction.getFromAccountNumber());
        response.setToAccountNumber(transaction.getToAccountNumber());
        response.setTransactionType(transaction.getTransactionType());
        response.setAmount(transaction.getAmount());
        response.setTransactionDate(transaction.getTransactionDate());
        response.setRemark(transaction.getRemarks());
        return response;
    }

}
