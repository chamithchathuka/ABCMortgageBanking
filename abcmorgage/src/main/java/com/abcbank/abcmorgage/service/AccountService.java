package com.abcbank.abcmorgage.service;

import com.abcbank.abcmorgage.domain.Account;
import com.abcbank.abcmorgage.domain.Customer;
import com.abcbank.abcmorgage.domain.MortgageDetails;
import com.abcbank.abcmorgage.dto.AccountType;
import com.abcbank.abcmorgage.dto.request.MortgageRequestDTO;
import com.abcbank.abcmorgage.dto.response.MortgageResponseDTO;
import com.abcbank.abcmorgage.repository.AccountRepository;
import com.abcbank.abcmorgage.repository.MortgageDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.abcbank.abcmorgage.Util.FORMATTER;

@Slf4j
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final MortgageDetailsRepository mortgageDetailsRepository;

    public AccountService(AccountRepository accountRepository, MortgageDetailsRepository mortgageDetailsRepository) {
        this.accountRepository = accountRepository;
        this.mortgageDetailsRepository = mortgageDetailsRepository;
    }

    @Transactional
    public MortgageResponseDTO createMortgageAccount(MortgageRequestDTO mortgageRequestDTO) {
        MortgageDetails mortgageDetails = null;
        if (String.valueOf(AccountType.MORTGAGE).equals(mortgageRequestDTO.getAccountType())) {

            BigDecimal mortgageAmount = BigDecimal.valueOf(mortgageRequestDTO.getMortgageAmount());
            BigDecimal propertyCost = BigDecimal.valueOf(mortgageRequestDTO.getPropertyCost());
            BigDecimal depositAmount = BigDecimal.valueOf(mortgageRequestDTO.getDepositAmount());

            //validate mortgage amount less than property amount
            if(mortgageAmount.compareTo(propertyCost) <= 0) {
                BigDecimal twentyPercent = mortgageAmount.multiply(BigDecimal.valueOf(0.20));

                if (depositAmount.compareTo(twentyPercent) >= 0) {
                    Account account = mapToEntityAccount(mortgageRequestDTO);
                    // create account first
                    Account accountSave = accountRepository.save(account);
                    // create mortgage details
                    mortgageRequestDTO.setCustomerId(accountSave.getCustomer());
                    mortgageDetails = mapToEntityMortgage(mortgageRequestDTO);

                    mortgageDetailsRepository.save(mortgageDetails);

                } else {
                    //TODO remove error messages to property file
                    throw new AccountCreationException("Deposit is more than or equal to 20% of the mortgage amount.");
                }

            }else{
                //TODO remove error messages to property file
                throw new AccountCreationException("Mortgage amount less than property amount.");
            }
        } else {
            //TODO remove error messages to property file
            throw new AccountCreationException("invalid Account Type");
        }
        return mapToResponseDTO(mortgageDetails);
    }


    private MortgageResponseDTO mapToResponseDTO(MortgageDetails mortgageDetails) {

        Long mortgageId = mortgageDetails.getAccountNumber();
        Long accountNumber = mortgageDetails.getAccount().getAccountNumber();
        String accountCreatedDate = mortgageDetails.getAccount().getAccountCreatedDate();
        return new MortgageResponseDTO(accountNumber, mortgageId, accountCreatedDate);
    }

    private Account mapToEntityAccount(MortgageRequestDTO mortgageDTO) {
        Account account = new Account();
        //mocked id
        account.setCustomer(mortgageDTO.getCustomerId());
        LocalDateTime now = LocalDateTime.now();
        account.setLastTransactionDate(now.format(FORMATTER));
        account.setAccountCreatedDate(now.format(FORMATTER));
        BeanUtils.copyProperties(mortgageDTO, account);


        return account;

    }



    private MortgageDetails mapToEntityMortgage(MortgageRequestDTO mortgageDTO) {
        MortgageDetails mortgageDetails = new MortgageDetails();
        BeanUtils.copyProperties(mortgageDTO, mortgageDetails);
        return mortgageDetails;
    }
}
