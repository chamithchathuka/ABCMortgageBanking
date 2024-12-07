package com.abcbank.abcmorgage.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MortgageDetails")
public class MortgageDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MortgageId")
    private Integer mortgageId;

    @NotNull
    @Column(name = "AccountNumber", nullable = false)
    private Long accountNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "MortgageType", nullable = false)
    private String mortgageType;

    @NotNull
    @DecimalMin(value = "1", message = "Property cost must be greater than 0")
    @Column(name = "PropertyCost", nullable = false, scale = 2)
    private BigDecimal propertyCost;

    @NotNull
    @DecimalMin(value = "1", message = "Mortgage amount must be greater than 0")
    @Column(name = "MortgageAmount", nullable = false, scale = 2)
    private BigDecimal mortgageAmount;

    @NotNull
    @DecimalMin(value = "1", message = "Deposit amount must be greater than 0")
    @Column(name = "DepositAmount", nullable = false, scale = 2)
    private BigDecimal depositAmount;

    @Column(name = "CreatedAt", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "UpdatedAt")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AccountNumber", referencedColumnName = "accountNumber", nullable = false, insertable = false, updatable = false)
    private Account account;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Integer getMortgageId() {
        return mortgageId;
    }

    public void setMortgageId(Integer mortgageId) {
        this.mortgageId = mortgageId;
    }

    public @NotNull Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NotNull Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public @NotNull @Size(max = 50) String getMortgageType() {
        return mortgageType;
    }

    public void setMortgageType(@NotNull @Size(max = 50) String mortgageType) {
        this.mortgageType = mortgageType;
    }

    public @NotNull @DecimalMin(value = "1", message = "Property cost must be greater than 0") BigDecimal getPropertyCost() {
        return propertyCost;
    }

    public void setPropertyCost(@NotNull @DecimalMin(value = "1", message = "Property cost must be greater than 0") BigDecimal propertyCost) {
        this.propertyCost = propertyCost;
    }

    public @NotNull @DecimalMin(value = "1", message = "Mortgage amount must be greater than 0") BigDecimal getMortgageAmount() {
        return mortgageAmount;
    }

    public void setMortgageAmount(@NotNull @DecimalMin(value = "1", message = "Mortgage amount must be greater than 0") BigDecimal mortgageAmount) {
        this.mortgageAmount = mortgageAmount;
    }

    public @NotNull @DecimalMin(value = "1", message = "Deposit amount must be greater than 0") BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(@NotNull @DecimalMin(value = "1", message = "Deposit amount must be greater than 0") BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
