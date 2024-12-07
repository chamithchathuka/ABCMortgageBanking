package com.abcbank.abcmorgage.service.impl;

import com.abcbank.abcmorgage.dto.AccessTokenDTO;
import com.abcbank.abcmorgage.dto.LoginRequest;
import com.abcbank.abcmorgage.exception.PasswordIncorectException;
import com.abcbank.abcmorgage.exception.UserNotFoundException;
import com.abcbank.abcmorgage.model.Customer;
import com.abcbank.abcmorgage.repository.CustomerRepository;
import com.abcbank.abcmorgage.service.CustomerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Value("${api.jwtSecret}")
    private String jwtSecret;

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public AccessTokenDTO login(LoginRequest loginRequest) {

        Optional<Customer> customerOptional = customerRepository.findByCustomerId(loginRequest.customerId());

        Customer customer = customerOptional
                .orElseThrow(() -> new UserNotFoundException("Customer not found"));

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean passwordMatches = bCryptPasswordEncoder.matches(loginRequest.password(), customer.getPassword());

        if(!passwordMatches) {
            throw new PasswordIncorectException("Password is Not Valid");
        }

        String accessToken = generateJwtToken(String.valueOf(customer.getCustomerId()));

        return new AccessTokenDTO(accessToken);
    }

    private String generateJwtToken(String userId) {

        long jwtExpirationMs = 30000;

        return Jwts.builder()
                .subject((userId))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSigningKey(jwtSecret))
                .compact();
    }

    private Key getSigningKey(String jwtSecret) {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}