package com.abcbank.abcmorgage.model;



import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TransferModel {

	
	@NotNull(message="can't be null")
	private Long FromAccountNumber;
	@NotNull(message="can't be null")
	private Long ToAccountNumber;
	@Min(value=1, message=" To be greater than zero")
	private Long Amount;
	@NotNull(message="please add your inputs")
	private String remarks;
	
	
	public Long getFromAccountNumber() {
		return FromAccountNumber;
	}
	public void setFromAccountNumber(Long fromAccountNumber) {
		FromAccountNumber = fromAccountNumber;
	}
	public Long getToAccountNumber() {
		return ToAccountNumber;
	}
	public void setToAccountNumber(Long toAccountNumber) {
		ToAccountNumber = toAccountNumber;
	}
	public Long getAmount() {
		return Amount;
	}
	public void setAmount(Long amount) {
		Amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	@Override
	public String toString() {
		return "TransferModel [FromAccountNumber=" + FromAccountNumber + ", ToAccountNumber=" + ToAccountNumber
				+ ", Amount=" + Amount + ", remarks=" + remarks + "]";
	}
	
	
	
	
	
}
