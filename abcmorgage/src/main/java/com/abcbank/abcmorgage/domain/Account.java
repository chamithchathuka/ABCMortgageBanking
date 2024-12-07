package com.abcbank.abcmorgage.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table( name ="account")
public class Account {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long accountNumber;

    @JoinColumn(name = "customer_id", nullable = false)
    private String customer;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private String lastTransactionDate;

    @Column(nullable = false)
    private String accountCreatedDate;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }


    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }


    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getLastTransactionDate() {
        return lastTransactionDate;
    }

    public void setLastTransactionDate(String lastTransactionDate) {
        this.lastTransactionDate = lastTransactionDate;
    }

    public String getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public void setAccountCreatedDate(String accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }
}
