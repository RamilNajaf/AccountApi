package com.demo.accountapi;

import com.demo.accountapi.dto.CreateAccountRequest;
import com.demo.accountapi.dto.convertor.CustomerDtoConvertor;
import com.demo.accountapi.model.Customer;
import com.demo.accountapi.repository.CustomerRepository;
import com.demo.accountapi.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

public  abstract class IntegrationTestSupprot {
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public AccountService accountService;

    @Autowired
    public CustomerDtoConvertor converter;


    public final ObjectMapper mapper = new ObjectMapper();


    protected CreateAccountRequest generateCreateAccountRequest(int initialCredit) {
        return generateCreateAccountRequest("customer-id", initialCredit);
    }


    protected CreateAccountRequest generateCreateAccountRequest(String customerId, int initialCredit) {
        return new CreateAccountRequest(customerId, new BigDecimal(initialCredit));
    }


    protected Customer generateCustomer() {
        return new Customer("customer-id", "customer-name", "customer-surname", List.of());
    }
}
