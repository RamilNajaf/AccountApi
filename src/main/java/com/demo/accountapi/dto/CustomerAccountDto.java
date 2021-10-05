package com.demo.accountapi.dto;

import com.demo.accountapi.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class CustomerAccountDto {
    private String id;
    private BigDecimal balance=BigDecimal.ZERO;
    private Set<TransactionDto> transactions;
    private LocalDateTime creationDate;

}
