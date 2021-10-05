package com.demo.accountapi.service;


import com.demo.accountapi.dto.AccountDto;
import com.demo.accountapi.dto.CreateAccountRequest;
import com.demo.accountapi.dto.convertor.AccountDtoConverter;
import com.demo.accountapi.model.Account;
import com.demo.accountapi.model.Customer;
import com.demo.accountapi.model.Transaction;
import com.demo.accountapi.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class AccountService {


    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter convertor;
    private final Clock clock;

    public AccountService(AccountRepository accountRepository, CustomerService customerService,Clock clock, AccountDtoConverter convertor) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.convertor=convertor;
        this.clock=clock;
    }

    public AccountDto createAccount(CreateAccountRequest request) {

        Customer customer =customerService.findCustomerById(request.getCustomerId());

        Account account = new Account(request.getInitialCredit(), LocalDateTime.now(),customer);

        if (request.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = new Transaction(
                    request.getInitialCredit(),
                    getLocalDateTimeNow(),
                    account);


          account.getTransactionSet().add(transaction);
        }
        return convertor.convertor(accountRepository.save(account));

    }
    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone());
    }
}
