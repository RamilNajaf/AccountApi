package com.demo.accountapi.dto.convertor;

import com.demo.accountapi.dto.AccountDto;
import com.demo.accountapi.model.Account;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {
   private  final CustomerDtoConvertor convertor;
   private  final TransactionDtoConvertor transactionDtoComvertor;


    public AccountDtoConverter(CustomerDtoConvertor convertor, TransactionDtoConvertor transactionDtoComvertor) {
        this.convertor=convertor;
        this.transactionDtoComvertor=transactionDtoComvertor;
    }

    public AccountDto convertor(Account from){
        return new AccountDto(from.getId(),
                from.getBalance(),
                from.getCreationDate(),
                convertor.convert(from.getCustomer()),
                from.getTransactionSet()
                        .stream()
                        .map(transaction -> transactionDtoComvertor.convert(transaction))
                        .collect(Collectors.toSet()));


    }
}
