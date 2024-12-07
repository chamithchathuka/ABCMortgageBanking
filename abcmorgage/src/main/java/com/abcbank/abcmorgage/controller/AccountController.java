package com.abcbank.abcmorgage.controller;

import com.abcbank.abcmorgage.dto.AccountSummaryResponse;
import com.abcbank.abcmorgage.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/summary/{customerId}")
    public ResponseEntity<AccountSummaryResponse> getAccountSummary(@PathVariable Long customerId) {
        AccountSummaryResponse accountSummary = accountService.getAccountSummary(customerId);
        return ResponseEntity.ok(accountSummary);
    }
}
