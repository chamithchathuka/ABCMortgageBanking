package com.abcbank.abcmorgage.repository;

import com.abcbank.abcmorgage.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {

}