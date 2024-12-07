package com.abcbank.abcmorgage.controller;

import com.abcbank.abcmorgage.dto.AccessTokenDTO;
import com.abcbank.abcmorgage.dto.LoginRequest;
import com.abcbank.abcmorgage.service.CustomerService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public AccessTokenDTO login(@RequestBody @Validated LoginRequest loginRequest) {

        return customerService.login(loginRequest);
    }
}