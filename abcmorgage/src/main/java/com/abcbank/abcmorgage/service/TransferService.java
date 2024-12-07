package com.abcbank.abcmorgage.service;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abcbank.abcmorgage.model.TransactionStatus;
import com.abcbank.abcmorgage.model.TransferModel;
import com.abcbank.abcmorgage.repository.TransferRepository;


import jakarta.transaction.Transactional;

@Service
public class TransferService {

	@Autowired
	private TransferRepository transferRepository; 
	
	@Autowired
	private TransactionStatus transactionStatus;
	
	@Transactional
	public TransactionStatus transService(TransferModel transferModel){
		  
		try {
		
		   transferModel.setFromAccountNumber(transferModel.getFromAccountNumber());
		   transferModel.setToAccountNumber(transferModel.getToAccountNumber());
		   transferModel.setAmount(transferModel.getAmount());
		   transferModel.setRemarks(transferModel.getRemarks());
		
		   transferRepository.save(transferModel);
		   
		  }catch(Exception e) {
			  e.getMessage();
		  }
		   return new TransactionStatus(UUID.randomUUID().toString(),"SUCCESS");
		
	}
	
}
