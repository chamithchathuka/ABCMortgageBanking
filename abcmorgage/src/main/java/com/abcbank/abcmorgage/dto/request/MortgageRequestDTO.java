package com.abcbank.abcmorgage.dto.request;

import com.abcbank.abcmorgage.dto.exception.ValidationException;

public class MortgageRequestDTO {

    private String customerId;
    private String accountType;
    private Double mortgageAmount;
    private Double propertyValue;
    private Double depositAmount;

    private String mortgageType;
    private Double propertyCost;
    private Double mortgageDepositAmount;

    public MortgageRequestDTO() {
    }

    public String getMortgageType() {
        if (mortgageType == null || mortgageType.trim().isEmpty()) {
            throw new ValidationException("MortgageType cannot be null or empty");
        }

        return mortgageType;
    }

    public Double getMortgageDepositAmount() {
        if (mortgageAmount == null) {
            throw new ValidationException("MortgageAmount cannot be null");
        }
        return mortgageAmount;
    }

    public Double getPropertyCost() {
        if (propertyCost == null) {
            throw new ValidationException("PropertyCost cannot be null");
        }
        return propertyCost;
    }


    public void setMortgageType(String mortgageType) {
        this.mortgageType = mortgageType;
    }

    public void setMortgageDepositAmount(Double mortgageDepositAmount) {
        this.mortgageDepositAmount = mortgageDepositAmount;
    }

    public void setPropertyCost(Double propertyCost) {
        this.propertyCost = propertyCost;
    }


    public String getCustomerId() {
        if (customerId == null) {
            throw new ValidationException("CustomerId is null");
        }
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        if (accountType == null) {
            throw new ValidationException("AccountType is null");
        }
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getMortgageAmount() {
        if (mortgageAmount == null) {
            throw new ValidationException("MortgageAmount is null");
        }
        return mortgageAmount;
    }

    public void setMortgageAmount(Double mortgageAmount) {
        this.mortgageAmount = mortgageAmount;
    }

    public Double getPropertyValue() {
        if (propertyValue == null) {
            throw new ValidationException("PropertyValue is null");
        }
        return propertyValue;
    }

    public void setPropertyValue(Double propertyValue) {
        this.propertyValue = propertyValue;
    }

    public Double getDepositAmount() {
        if (depositAmount == null) {
            throw new ValidationException("DepositAmount is null");
        }
        return depositAmount;
    }

    public void setDepositAmount(Double depositAmount) {
        this.depositAmount = depositAmount;
    }


}
