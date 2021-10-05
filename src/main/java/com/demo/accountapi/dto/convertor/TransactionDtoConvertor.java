package com.demo.accountapi.dto.convertor;

import com.demo.accountapi.dto.TransactionDto;
import com.demo.accountapi.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConvertor {
    public TransactionDto convert(Transaction from) {
        return new TransactionDto(from.getId(),
                from.getType(),
                from.getAmount(),
                from.getTransactionDate());
    }
}
