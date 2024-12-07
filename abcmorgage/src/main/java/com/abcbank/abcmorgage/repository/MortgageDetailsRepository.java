package com.abcbank.abcmorgage.repository;

import com.abcbank.abcmorgage.domain.MortgageDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MortgageDetailsRepository extends JpaRepository<MortgageDetails, Integer> {

}
