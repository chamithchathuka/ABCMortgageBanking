package com.abcbank.abcmorgage.controller;


import com.abcbank.abcmorgage.dto.request.MortgageRequestDTO;
import com.abcbank.abcmorgage.dto.response.MortgageResponseDTO;
import com.abcbank.abcmorgage.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/account")
public class AccountController {

    AccountService accountService;

    AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MortgageResponseDTO> createMortgageAccount(@Valid @RequestBody MortgageRequestDTO mortgageRequestDTO) {
        //todo tobe taken from verified user from token hard coded
        mortgageRequestDTO.setCustomerId("12");

        MortgageResponseDTO mortgageAccount = accountService.createMortgageAccount(mortgageRequestDTO);
        return ResponseEntity.ok(mortgageAccount);
    }

}
