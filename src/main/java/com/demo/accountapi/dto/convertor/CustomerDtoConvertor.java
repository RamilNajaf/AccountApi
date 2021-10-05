package com.demo.accountapi.dto.convertor;

import com.demo.accountapi.dto.AccountCustomerDto;
import com.demo.accountapi.dto.CustomerAccountDto;
import com.demo.accountapi.dto.CustomerDto;
import com.demo.accountapi.model.Customer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerDtoConvertor {
    private final CustomerAccountDtoConverter customerAccountDtoConverter;

    private final  TransactionDtoConvertor convertor;


    public CustomerDtoConvertor(CustomerAccountDtoConverter customerAccountDtoConverter, TransactionDtoConvertor convertor) {
        this.customerAccountDtoConverter = customerAccountDtoConverter;
        this.convertor = convertor;
    }

    public AccountCustomerDto convert(Customer from){

        if(from==null){
            return new AccountCustomerDto("","","");
        }
        return new AccountCustomerDto(from.getId(),from.getName(),from.getSurname());


    }


    public CustomerDto convertToCustomerDto(Customer from) {
        return new CustomerDto(
                from.getId(),
                from.getName(),
                from.getSurname(),
                from.getAccounts()
                        .stream()
                        .map(customerAccountDtoConverter::convert)
                        .collect(Collectors.toSet()));

    }



}
