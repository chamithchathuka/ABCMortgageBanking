package com.abcbank.abcmorgage.service;

import com.abcbank.abcmorgage.dto.AccessTokenDTO;
import com.abcbank.abcmorgage.dto.LoginRequest;
import org.springframework.stereotype.Service;

public interface CustomerService {

    public AccessTokenDTO login(LoginRequest loginRequest);
}
