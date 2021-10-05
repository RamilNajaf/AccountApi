package com.demo.accountapi.dto;


import com.demo.accountapi.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionDto {

    private  String id;
    private TransactionType type = TransactionType.INITIAL;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
}
