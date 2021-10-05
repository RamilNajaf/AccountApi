package com.demo.accountapi.dto;

import com.demo.accountapi.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class AccountDto {
    private String id;
    private BigDecimal balance=BigDecimal.ZERO;
    private LocalDateTime creationDate;
    private AccountCustomerDto customer;
    private Set<TransactionDto> transactions;


}
