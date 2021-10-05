package com.demo.accountapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCustomerDto {
    private  String id;
    private  String name;
    private  String surname;
}
