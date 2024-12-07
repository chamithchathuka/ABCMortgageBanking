package com.abcbank.abcmorgage;

import com.abcbank.abcmorgage.service.TransactionService;
import com.abcbank.abcmorgage.dto.Transaction;
import com.abcbank.abcmorgage.dto.TransactionResponse;
import com.abcbank.abcmorgage.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    private Transaction transaction1;
    private Transaction transaction2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Set up dummy transaction data
        transaction1 = new Transaction();
        transaction1.setFromAccountNumber(101L);
        transaction1.setToAccountNumber(102L);
        transaction1.setTransactionType("CREDIT");
        transaction1.setAmount(new BigDecimal("5000"));
        transaction1.setTransactionDate(LocalDateTime.of(2024, 12, 7, 11, 45, 0, 0));
        transaction1.setRemarks("Credited from 102");

        transaction2 = new Transaction();
        transaction2.setFromAccountNumber(101L);
        transaction2.setToAccountNumber(103L);
        transaction2.setTransactionType("DEBIT");
        transaction2.setAmount(new BigDecimal("2000"));
        transaction2.setTransactionDate(LocalDateTime.of(2024, 12, 7, 12, 30, 0, 0));
        transaction2.setRemarks("Debited to 103");
    }

    @Test
    public void testGetTransactionSummary() {
        // Arrange: Mock the repository to return a list of transactions
        when(transactionRepository.findByFromAccountNumberOrToAccountNumber(101L, 101L))
                .thenReturn(Arrays.asList(transaction1, transaction2));

        // Act: Call the service method
        List<TransactionResponse> result = transactionService.getTransactionSummary(101L);

        // Assert: Verify the response contains the expected transaction data
        assertNotNull(result);
        assertEquals(2, result.size());

        // Verify transaction 1
        TransactionResponse response1 = result.get(0);
        assertEquals(101L, response1.getFromAccountNumber());
        assertEquals(102L, response1.getToAccountNumber());
        assertEquals("CREDIT", response1.getTransactionType());
        assertEquals(new BigDecimal("5000"), response1.getAmount());
        assertEquals(LocalDateTime.of(2024, 12, 7, 11, 45, 0, 0), response1.getTransactionDate());
        assertEquals("Credited from 102", response1.getRemark());

        // Verify transaction 2
        TransactionResponse response2 = result.get(1);
        assertEquals(101L, response2.getFromAccountNumber());
        assertEquals(103L, response2.getToAccountNumber());
        assertEquals("DEBIT", response2.getTransactionType());
        assertEquals(new BigDecimal("2000"), response2.getAmount());
        assertEquals(LocalDateTime.of(2024, 12, 7, 12, 30, 0, 0), response2.getTransactionDate());
        assertEquals("Debited to 103", response2.getRemark());
    }
}