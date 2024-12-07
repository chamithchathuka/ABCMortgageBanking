package com.abcbank.abcmorgage.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.GeneratedValue;

public class TransactionStatus {

	@GeneratedValue(generator="uuid")
    private String transactionId;
	private String message;
	
	
	
	public TransactionStatus(String transactionId, String message) {
		super();
		this.transactionId = transactionId;
		this.message = message;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
