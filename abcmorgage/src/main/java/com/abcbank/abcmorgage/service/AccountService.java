package com.abcbank.abcmorgage.service;

import com.abcbank.abcmorgage.dto.Account;
import com.abcbank.abcmorgage.dto.AccountSummaryResponse;
import com.abcbank.abcmorgage.exception.AccountNotFoundException;
import com.abcbank.abcmorgage.repository.AccountRepository;
import com.abcbank.abcmorgage.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public AccountSummaryResponse getAccountSummary(Long customerId) {
        // Fetch the accounts for the customer
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        if (accounts.isEmpty()) {
            throw new AccountNotFoundException("No accounts found for the customer");
        }

        // Map the accounts to the response DTO
        List<AccountSummaryResponse.AccountDetails> accountDetails = accounts.stream()
                .map(account -> {
                    AccountSummaryResponse.AccountDetails details = new AccountSummaryResponse.AccountDetails();
                    details.setAccountNumber(account.getAccountNumber());
                    details.setAccountType(account.getAccountType());
                    details.setAccountBalance(account.getAccountBalance());

                    // Fetch the last transaction date for the account
                    LocalDateTime lastTransactionDate = transactionRepository.findLastTransactionDateByAccountNumber(account.getAccountNumber());
                    details.setLastTransactionDate(lastTransactionDate);

                    return details;
                })
                .collect(Collectors.toList());

        // Return the account summary response
        AccountSummaryResponse response = new AccountSummaryResponse();
        response.setCustomerId(customerId); // Set the customerId, assuming customer information is fetched from another source if needed
        response.setCustomerName("Customer Name"); // Placeholder, you can query the customer's name from a customer repository
        response.setAccounts(accountDetails);

        return response;
    }
}