package com.abcbank.abcmorgage.dto;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountNumber")
    private Long accountNumber;

    @Column(name = "CustomerId", nullable = false)
    private Long customerId;

    @Column(name = "AccountType", nullable = false)
    private String accountType;

    @Column(name = "AccountBalance", nullable = false)
    private BigDecimal accountBalance;

    @Column(name = "LastTransactionDate")
    private LocalDateTime lastTransactionDate;

    @Column(name = "AccountCreatedDate", nullable = false, updatable = false)
    private LocalDateTime accountCreatedDate;

    // Getters and Setters due to lombok annotations didn't create getters and setters for me(got errors for all getter and setter methods)
    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public LocalDateTime getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public void setAccountCreatedDate(LocalDateTime accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }
}