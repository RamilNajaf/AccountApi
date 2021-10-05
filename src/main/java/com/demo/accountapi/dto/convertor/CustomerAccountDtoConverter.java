package com.demo.accountapi.dto.convertor;

import com.demo.accountapi.dto.CustomerAccountDto;
import com.demo.accountapi.model.Account;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CustomerAccountDtoConverter {
    private final TransactionDtoConvertor transactionDtoConverter;

    public CustomerAccountDtoConverter(TransactionDtoConvertor converter) {
        this.transactionDtoConverter = converter;
    }

    public CustomerAccountDto convert(Account from) {
        return new CustomerAccountDto(
                Objects.requireNonNull(from.getId()),
                from.getBalance(),
                from.getTransactionSet()
                        .stream()
                        .map(transaction -> transactionDtoConverter.convert(transaction))
                        .collect(Collectors.toSet()),
                from.getCreationDate());
    }
}
