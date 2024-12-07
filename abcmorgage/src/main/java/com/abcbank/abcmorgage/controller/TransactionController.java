package com.abcbank.abcmorgage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.abcmorgage.model.TransactionStatus;
import com.abcbank.abcmorgage.model.TransferModel;
import com.abcbank.abcmorgage.service.TransferService;



@RestController
public class TransactionController {

	@Autowired
	private TransferService transferservice;
	
    @PostMapping("/transfer")
	public TransactionStatus transferamount(@RequestBody TransferModel transfermodel){
		return transferservice.transService(transfermodel);
	}
	
}
