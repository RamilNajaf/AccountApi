package com.demo.accountapi.controller;


import com.demo.accountapi.IntegrationTestSupprot;
import com.demo.accountapi.dto.CustomerDto;
import com.demo.accountapi.dto.convertor.CustomerDtoConvertor;
import com.demo.accountapi.model.Customer;
import com.demo.accountapi.repository.CustomerRepository;
import com.demo.accountapi.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllertTest extends IntegrationTestSupprot {





    @Test //?
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomerDto() throws Exception {
        Customer customer = customerRepository.save(generateCustomer());
        accountService.createAccount(generateCreateAccountRequest(customer.getId(), 100));

        CustomerDto expected = converter.convertToCustomerDto(
                customerRepository.getById(customer.getId()));

        this.mockMvc.perform(get("/v1/customer/" + customer.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(expected), false))
                .andReturn();
    }

    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldReturnHttpNotFound() throws Exception {

        this.mockMvc.perform(get( "/v1/customer/"+ "non-exists-customer"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
