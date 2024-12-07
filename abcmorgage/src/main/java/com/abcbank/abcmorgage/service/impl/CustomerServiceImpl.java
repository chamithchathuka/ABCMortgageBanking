package com.abcbank.abcmorgage.service.impl;

import com.abcbank.abcmorgage.dto.AccessTokenDTO;
import com.abcbank.abcmorgage.dto.LoginRequest;
import com.abcbank.abcmorgage.exception.WrongCredentialsException;
import com.abcbank.abcmorgage.service.CustomerService;
import com.abcbank.abcmorgage.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Value("${api.jwtSecret}")
    private String jwtSecret;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public CustomerServiceImpl(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AccessTokenDTO login(LoginRequest loginRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(),
                    loginRequest.password()));
        } catch (AuthenticationException e) {
            throw new WrongCredentialsException("Invalid username or password");
        }

        String accessToken = jwtUtil.generateToken(loginRequest.username());

        return new AccessTokenDTO(accessToken);
    }
}