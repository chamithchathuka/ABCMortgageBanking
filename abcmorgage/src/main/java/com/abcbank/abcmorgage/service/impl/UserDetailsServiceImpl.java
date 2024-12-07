package com.abcbank.abcmorgage.service.impl;

import com.abcbank.abcmorgage.exception.UserNotFoundException;
import com.abcbank.abcmorgage.model.Customer;
import com.abcbank.abcmorgage.repository.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public UserDetailsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<Customer> customerOptional = customerRepository.findByUsername(username);

        Customer customer = customerOptional
                .orElseThrow(() -> new UserNotFoundException("Customer not found"));

        return User.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .build();
    }
}