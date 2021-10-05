package com.demo.accountapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.DecimalMin;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CreateAccountRequest {


    @NotBlank(message = "{NotNull.message}")
    private String customerId;



    @DecimalMin(value = "0.0", inclusive = false,message = "{NotNegative.message}")
    private BigDecimal initialCredit;

}
