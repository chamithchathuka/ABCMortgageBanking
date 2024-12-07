package com.abcbank.abcmorgage;

import com.abcbank.abcmorgage.controller.TransactionController;
import com.abcbank.abcmorgage.dto.Transaction;
import com.abcbank.abcmorgage.dto.TransactionResponse;
import com.abcbank.abcmorgage.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc will allow us to simulate HTTP requests

    @Mock
    private TransactionService transactionService; // Automatically mock the service layer

    private TransactionResponse transactionResponse1;
    private TransactionResponse transactionResponse2;

    @BeforeEach
    public void setUp() {
        // Initialize the mock objects
        transactionResponse1 = new TransactionResponse();
        transactionResponse1.setFromAccountNumber(101L);
        transactionResponse1.setToAccountNumber(102L);
        transactionResponse1.setAmount(new BigDecimal("5000"));
        transactionResponse1.setTransactionDate(LocalDateTime.of(2024, 12, 7, 11, 45, 0, 0)); // Set transaction date
        transactionResponse1.setRemark("Credited from 102"); // Set remark

        transactionResponse2 = new TransactionResponse();
        transactionResponse2.setFromAccountNumber(101L);
        transactionResponse2.setToAccountNumber(103L);
        transactionResponse2.setAmount(new BigDecimal("2000"));
        transactionResponse2.setTransactionDate(LocalDateTime.of(2024, 12, 7, 12, 30, 0, 0)); // Set transaction date
        transactionResponse2.setRemark("Debited to 103"); // Set remark
    }

    @Test
    public void testGetTransactionSummary() throws Exception {
        // Arrange: Mock the service method
        when(transactionService.getTransactionSummary(101L)).thenReturn(Arrays.asList(transactionResponse1, transactionResponse2));

        // Act & Assert: Call the controller endpoint and validate the response
        mockMvc.perform(get("/transaction/summary/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fromAccountNumber").value(101L))
                .andExpect(jsonPath("$[0].toAccountNumber").value(102L))
                .andExpect(jsonPath("$[0].amount").value(5000))
                .andExpect(jsonPath("$[0].transactionDate").value("2024-12-07T11:45:00")) // Validate date format
                .andExpect(jsonPath("$[0].remark").value("Credited from 102")) // Validate remark

                .andExpect(jsonPath("$[1].fromAccountNumber").value(101L))
                .andExpect(jsonPath("$[1].toAccountNumber").value(103L))
                .andExpect(jsonPath("$[1].amount").value(2000))
                .andExpect(jsonPath("$[1].transactionDate").value("2024-12-07T12:30:00")) // Validate date format
                .andExpect(jsonPath("$[1].remark").value("Debited to 103")); // Validate remark
    }
}