package com.abcbank.abcmorgage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.abcbank.abcmorgage.model.TransferModel;



@Repository
public interface TransferRepository extends JpaRepository<TransferModel,Object> {

    
}
