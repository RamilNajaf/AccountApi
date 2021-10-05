package com.demo.accountapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class CustomerDto {

    private String id;
    private String name;
    private String surname;
    private Set<CustomerAccountDto> accounts;
}
