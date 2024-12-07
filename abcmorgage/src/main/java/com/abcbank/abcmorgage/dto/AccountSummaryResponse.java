package com.abcbank.abcmorgage.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AccountSummaryResponse {

    private Long customerId;
    private String customerName;
    private List<AccountDetails> accounts;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<AccountDetails> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDetails> accounts) {
        this.accounts = accounts;
    }

    // Getters and Setters

    public static class AccountDetails {
        private Long accountNumber;
        private String accountType;
        private BigDecimal accountBalance;
        private LocalDateTime lastTransactionDate;

        public Long getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(Long accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public BigDecimal getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(BigDecimal accountBalance) {
            this.accountBalance = accountBalance;
        }

        public LocalDateTime getLastTransactionDate() {
            return lastTransactionDate;
        }

        public void setLastTransactionDate(LocalDateTime lastTransactionDate) {
            this.lastTransactionDate = lastTransactionDate;
        }


    }

}
