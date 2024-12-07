package com.abcbank.abcmorgage.repository;

import com.abcbank.abcmorgage.dto.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Query to find transactions where the account is involved (either as sender or receiver)
    List<Transaction> findByFromAccountNumberOrToAccountNumber(Long fromAccountNumber, Long toAccountNumber);

    @Query("SELECT t.transactionDate FROM Transaction t WHERE t.fromAccountNumber = ?1 OR t.toAccountNumber = ?1 ORDER BY t.transactionDate DESC")
    LocalDateTime findLastTransactionDateByAccountNumber(Long accountNumber);


}
