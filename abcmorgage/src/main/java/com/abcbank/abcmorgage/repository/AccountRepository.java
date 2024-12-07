package com.abcbank.abcmorgage.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcbank.abcmorgage.model.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

	List<AccountEntity> findByCustomer_CustomerId(int customerId);
	
	
}
