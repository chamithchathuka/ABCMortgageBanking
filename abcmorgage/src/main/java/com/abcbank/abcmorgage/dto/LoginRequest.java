package com.abcbank.abcmorgage.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest (
        @NotEmpty long customerId,
        @NotEmpty String password){
}