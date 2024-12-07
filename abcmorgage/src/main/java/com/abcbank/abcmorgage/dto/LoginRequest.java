package com.abcbank.abcmorgage.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest (
        @NotEmpty String username,
        @NotEmpty String password){
}