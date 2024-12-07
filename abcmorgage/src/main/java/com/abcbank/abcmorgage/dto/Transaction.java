package com.abcbank.abcmorgage.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private Long fromAccountNumber;

    @Column(nullable = false)
    private Long toAccountNumber;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    private String remarks;

    // Getters and Setters due to lombok annotations didn't create getters and setters for me(got errors for all getter and setter methods)
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}